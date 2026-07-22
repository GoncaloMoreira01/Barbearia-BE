package com.example.barbearia_be.service;

import com.example.barbearia_be.constants.RolesEnum;
import com.example.barbearia_be.dto.users.CreateUpdateUserDTO;
import com.example.barbearia_be.dto.users.UserIdName;
import com.example.barbearia_be.dto.users.UserLoginRequest;
import com.example.barbearia_be.dto.users.UserLoginResponse;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final IUsersRepo iUsersRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserLoginResponse create(CreateUpdateUserDTO userDto) {
        if (iUsersRepo.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        Users user = new Users(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getName(), RolesEnum.CLIENT.getId());
        Users savedUser = iUsersRepo.save(user);
        return new UserLoginResponse(savedUser.getId(), savedUser.getName(), savedUser.getRole());

    }

    @Transactional
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Users user = iUsersRepo.getUserByEmail(userLoginRequest.getEmail());
        if (user != null && passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            return new UserLoginResponse(user.getId(), user.getName(), user.getRole());
        }
        return null;
    }

    @Transactional
    public List<UserIdName> getBarbers() {
        List<UserIdName> barbersList = new ArrayList<>();
        List<Users> barbers = iUsersRepo.getBarbers();
        if (barbers != null) {
            for (Users barber : barbers) {
                UserIdName userIdName = new UserIdName(barber.getId(), barber.getName());
                barbersList.add(userIdName);
            }
        }
        return barbersList;
    }
}
