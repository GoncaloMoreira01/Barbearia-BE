package com.example.barbearia_be.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private Integer role;

    public Users(Long id, String username, String password, String name, Integer role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Users(String username, String password, String name, Integer role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public Users() {

    }
}
