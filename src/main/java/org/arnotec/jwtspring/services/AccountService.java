package org.arnotec.jwtspring.services;

import org.arnotec.jwtspring.entities.AppRole;
import org.arnotec.jwtspring.entities.AppUser;

public interface AccountService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppUser findUserByUsername(String username);
}
