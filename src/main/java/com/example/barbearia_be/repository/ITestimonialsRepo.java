package com.example.barbearia_be.repository;

import com.example.barbearia_be.model.Testimonials;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITestimonialsRepo extends JpaRepository<Testimonials, Long> {
    @Lock(LockModeType.NONE)
    @Query(value = "SELECT t FROM Testimonials t")
    List<Testimonials> getAllTestimonials();
}
