package com.example.barbearia_be.service;

import com.example.barbearia_be.dto.CreateUpdateUserDTO;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsersService {

    private final IUsersRepo iUsersRepo;
    private final PasswordEncoder passwordEncoder;

    public CreateUpdateUserDTO create(CreateUpdateUserDTO userDto) {
        Users user = new Users(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getName());
        Users savedUser = iUsersRepo.save(user);
        return new CreateUpdateUserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getName());
    }
}
