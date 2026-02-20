package com.example.barbearia_be.repository;

import com.example.barbearia_be.model.Users;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUsersRepo extends JpaRepository<Users, Long> {

    @Lock(LockModeType.NONE)
    @Query(value = "SELECT u FROM Users u where u.username = :username")
    Users getUserByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
}
