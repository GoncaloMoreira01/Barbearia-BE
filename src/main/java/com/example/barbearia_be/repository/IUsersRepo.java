package com.example.barbearia_be.repository;

import com.example.barbearia_be.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepo extends JpaRepository<Users, Long> {
}
