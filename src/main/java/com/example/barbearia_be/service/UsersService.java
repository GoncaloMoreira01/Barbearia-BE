package com.example.barbearia_be.service;

import com.example.barbearia_be.constants.Roles;
import com.example.barbearia_be.dto.CreateUpdateUserDTO;
import com.example.barbearia_be.dto.UserLoginRequest;
import com.example.barbearia_be.dto.UserLoginResponse;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UsersService {

    private final IUsersRepo iUsersRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateUpdateUserDTO create(CreateUpdateUserDTO userDto) {
        boolean usernameExists = iUsersRepo.existsByUsername(userDto.getUsername());
        if (!usernameExists) {
            Users user = new Users(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getName(), Roles.CLIENT.getId());
            Users savedUser = iUsersRepo.save(user);
            return new CreateUpdateUserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getName());
        }
        return null;
    }

    @Transactional
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Users user = iUsersRepo.getUserByUsername(userLoginRequest.getName());
        if (user != null && passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            return new UserLoginResponse(user.getId(), user.getName(), user.getRole());
        }
        return null;
    }
}
