package org.arnotec.jwtspring;

import org.arnotec.jwtspring.entities.AppRole;
import org.arnotec.jwtspring.entities.AppUser;
import org.arnotec.jwtspring.entities.Task;
import org.arnotec.jwtspring.repositories.TaskRepository;
import org.arnotec.jwtspring.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class JwtSpringApplication implements CommandLineRunner {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		accountService.saveUser(new AppUser(null,"admin","1234", null));
		accountService.saveUser(new AppUser(null,"user","1234", null));
		accountService.saveRole(new AppRole(null, "ADMIN"));
		accountService.saveRole(new AppRole(null, "USER"));
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		accountService.addRoleToUser("user", "USER");

		Stream.of("T1", "T2", "T3").forEach(t->{
			taskRepository.save(new Task(null,t));
		});
		taskRepository.findAll().forEach(t->{
			System.out.println("Tache : "+t.getTaskName());
		});
	}

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
}
