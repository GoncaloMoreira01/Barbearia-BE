package com.example.barbearia_be.dto.testimonial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateTestimonial {
    private long userId;
    private Integer stars;
    private String description;
}
