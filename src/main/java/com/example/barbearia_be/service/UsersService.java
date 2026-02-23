package com.example.barbearia_be.service;

import com.example.barbearia_be.constants.Roles;
import com.example.barbearia_be.dto.users.CreateUpdateUserDTO;
import com.example.barbearia_be.dto.users.UserLoginRequest;
import com.example.barbearia_be.dto.users.UserLoginResponse;
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
        boolean usernameExists = iUsersRepo.existsByEmail(userDto.getUsername());
        if (!usernameExists) {
            Users user = new Users(userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getName(), Roles.CLIENT.getId());
            Users savedUser = iUsersRepo.save(user);
            return new CreateUpdateUserDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
        }
        return null;
    }

    @Transactional
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Users user = iUsersRepo.getUserByEmail(userLoginRequest.getEmail());
        if (user != null && passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            return new UserLoginResponse(user.getId(), user.getName(), user.getRole());
        }
        return null;
    }
}
