package com.example.barbearia_be.repository;

import com.example.barbearia_be.model.Testimonials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITestimonialsRepo extends JpaRepository<Testimonials, Long> {
}
