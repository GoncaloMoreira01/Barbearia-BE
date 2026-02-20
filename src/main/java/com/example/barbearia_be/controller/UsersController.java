package com.example.barbearia_be.controller;

import com.example.barbearia_be.dto.CreateUpdateUserDTO;
import com.example.barbearia_be.dto.UserLoginRequest;
import com.example.barbearia_be.dto.UserLoginResponse;
import com.example.barbearia_be.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<CreateUpdateUserDTO> createUser(@RequestBody CreateUpdateUserDTO user) {
        try {
            CreateUpdateUserDTO createdUser = usersService.create(user);
            if (createdUser != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            UserLoginResponse userLoginResponse = usersService.login(userLoginRequest);
            if (userLoginResponse != null) {
                return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
