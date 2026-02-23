package com.example.barbearia_be.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateUserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;

    public CreateUpdateUserDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public CreateUpdateUserDTO(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
}
