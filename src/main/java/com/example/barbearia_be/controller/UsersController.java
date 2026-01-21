package com.example.barbearia_be.controller;

import com.example.barbearia_be.dto.CreateUpdateUserDTO;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/create")
    public ResponseEntity<CreateUpdateUserDTO> createUser(@RequestBody CreateUpdateUserDTO user) {
        try {
            CreateUpdateUserDTO createdUser = usersService.create(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
