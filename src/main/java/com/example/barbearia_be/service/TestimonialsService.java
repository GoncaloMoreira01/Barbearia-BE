package com.example.barbearia_be.service;

import com.example.barbearia_be.dto.testimonial.CreateTestimonial;
import com.example.barbearia_be.dto.testimonial.TestimonialListObject;
import com.example.barbearia_be.dto.users.CreateUpdateUserDTO;
import com.example.barbearia_be.model.Testimonials;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.ITestimonialsRepo;
import com.example.barbearia_be.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TestimonialsService {

    private ITestimonialsRepo iTestimonialsRepo;
    private IUsersRepo iUsersRepo;

    @Transactional
    public Testimonials create(CreateTestimonial createTestimonial) {
        Users user = iUsersRepo.getUserById(createTestimonial.getUserId());
        Testimonials testimonials = new Testimonials(user, createTestimonial.getDescription(), createTestimonial.getStars());
        return iTestimonialsRepo.save(testimonials);
    }

    @Transactional
    public List<TestimonialListObject> getTestimonialsList() {
        List<Testimonials> testimonialsList = iTestimonialsRepo.getAllTestimonials();
        List<TestimonialListObject> testimonialsObjectList = new ArrayList<>();
        if (testimonialsList != null) {
            for (Testimonials testimonials : testimonialsList) {
                testimonialsObjectList.add(new TestimonialListObject(testimonials.getStars(), testimonials.getDescription(), testimonials.getUser().getName()));
            }
        }
        return testimonialsObjectList;
    }
}
