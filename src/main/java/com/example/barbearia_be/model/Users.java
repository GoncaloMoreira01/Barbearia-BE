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

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private Integer role;

    public Users(Long id, String username, String name, Integer role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public Users() {

    }
}
