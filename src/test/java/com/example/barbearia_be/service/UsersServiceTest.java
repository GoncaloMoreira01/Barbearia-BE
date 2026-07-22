package com.example.barbearia_be.service;

import com.example.barbearia_be.constants.RolesEnum;
import com.example.barbearia_be.dto.users.CreateUpdateUserDTO;
import com.example.barbearia_be.dto.users.UserLoginRequest;
import com.example.barbearia_be.dto.users.UserLoginResponse;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IUsersRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    @Mock
    private IUsersRepo usersRepo;
    @InjectMocks
    private UsersService usersService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldCreateUserWhenDataIsValid() {
        CreateUpdateUserDTO userDto = new CreateUpdateUserDTO(1L, "clientenovo@gmail.com", "123456", "Cliente Novo");
        Users savedUser = new Users("cliente@gmail.com", "encodedPassword", "Cliente", RolesEnum.CLIENT.getId());
        savedUser.setId(1L);

        when(usersRepo.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(usersRepo.save(any(Users.class))).thenReturn(savedUser);

        UserLoginResponse response = usersService.create(userDto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Cliente", response.getName());
        assertEquals(RolesEnum.CLIENT.getId(), response.getRole());

        verify(usersRepo).existsByEmail(userDto.getEmail());
        verify(passwordEncoder).encode(userDto.getPassword());
        verify(usersRepo).save(any(Users.class));

    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        CreateUpdateUserDTO userDto = new CreateUpdateUserDTO(1L, "barbeiro1@gmail.com", "123", "John");

        when(usersRepo.existsByEmail(userDto.getEmail())).thenReturn(true);

        Exception exception = assertThrows(
                Exception.class,
                () -> usersService.create(userDto)
        );

        assertEquals("Email already exists.", exception.getMessage());

        verify(usersRepo).existsByEmail(userDto.getEmail());
        verify(usersRepo, never()).save(any(Users.class));
    }

    @Test
    void shouldLoginWhenCredentialsAreValid() {
        UserLoginRequest request = new UserLoginRequest("barbeiro1@gmail.com", "12345");

        Users user = new Users(
                "barbeiro1@gmail.com",
                "encodedPassword",
                "Barbeiro 1",
                RolesEnum.CLIENT.getId());
        user.setId(1L);

        when(usersRepo.getUserByEmail(request.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);

        UserLoginResponse response = usersService.login(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());

        verify(usersRepo).getUserByEmail(request.getEmail());
        verify(passwordEncoder).matches(request.getPassword(), user.getPassword());
    }
}
