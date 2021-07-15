package org.arnotec.jwtspring.api;

import org.arnotec.jwtspring.entities.AppUser;
import org.arnotec.jwtspring.services.AccountService;
import org.arnotec.jwtspring.web.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/register")
    @Transactional
    public AppUser register(@RequestBody RegisterForm registerForm) {
        if(!registerForm.getPassword().equals(registerForm.getRepassword())){
            throw new RuntimeException("You must confirm your password");
        }
        AppUser user = accountService.findUserByUsername(registerForm.getUsername());
        if(user!=null){
            throw new RuntimeException("This user already exist");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(registerForm.getUsername());
        appUser.setPassword(registerForm.getPassword());
        accountService.saveUser(appUser);
        accountService.addRoleToUser(registerForm.getUsername(), "USER");
        return appUser;
    }
}
