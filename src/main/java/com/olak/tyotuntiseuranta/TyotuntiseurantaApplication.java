package com.olak.tyotuntiseuranta;

import com.olak.tyotuntiseuranta.web.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.olak.tyotuntiseuranta.web.domain.UserRepository;

@SpringBootApplication
public class TyotuntiseurantaApplication {

    public static void main(String[] args) {
		SpringApplication.run(TyotuntiseurantaApplication.class, args);
	}
	@Bean
	public CommandLineRunner addUser(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args->{
			if (userRepository.findByUsername("admin").isEmpty()){
				User admin = new User();
				admin.setUsername("admin");
				admin.setFirstname("Ola");
                admin.setLastname("Karemo");
                admin.setPasswordHash(passwordEncoder.encode("password"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setFirstname("Testi");
                user.setLastname("Kayttaja");
                user.setPasswordHash(passwordEncoder.encode("password"));
                user.setRole("ROLE_USER");
                userRepository.save(user);
            }
		};
	}
}
