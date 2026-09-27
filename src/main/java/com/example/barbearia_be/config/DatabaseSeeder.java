package com.example.barbearia_be.config;

import com.example.barbearia_be.constants.RolesEnum;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IUsersRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedBarber(IUsersRepo usersRepo, PasswordEncoder passwordEncoder) {
        return args -> {
            String email = "barbeiro@gmail.com";
            if (!usersRepo.existsByEmail(email)) {
                Users barber = new Users(
                        email,
                        passwordEncoder.encode("12345"),
                        "Barbeiro",
                        RolesEnum.BARBER.getId()
                );
                usersRepo.save(barber);
            }
        };
    }
}
