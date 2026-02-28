package com.example.barbearia_be.controller;

import com.example.barbearia_be.dto.testimonial.CreateTestimonial;
import com.example.barbearia_be.dto.users.CreateUpdateUserDTO;
import com.example.barbearia_be.model.Testimonials;
import com.example.barbearia_be.service.TestimonialsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("testimonials")
public class TestimonialsController {

    private final TestimonialsService testimonialsService;

    @PostMapping("/create")
    public ResponseEntity<String> createTestimonial(@RequestBody CreateTestimonial testimonial) {
        try {
            Testimonials createTestimonial = testimonialsService.create(testimonial);
            if (createTestimonial != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nok");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
