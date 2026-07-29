package com.example.barbearia_be.service;

import com.example.barbearia_be.dto.testimonial.CreateTestimonial;
import com.example.barbearia_be.dto.testimonial.TestimonialListObject;
import com.example.barbearia_be.model.Testimonials;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.ITestimonialsRepo;
import com.example.barbearia_be.repository.IUsersRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestimonialsServiceTest {

    @Mock
    private ITestimonialsRepo iTestimonialsRepo;

    @Mock
    private IUsersRepo iUsersRepo;

    @InjectMocks
    private TestimonialsService testimonialsService;

    @Test
    void shouldCreateTestimonial() {
        CreateTestimonial request = new CreateTestimonial(1L, 5, "Awesome barbershop");
        Users user = new Users("cliente@gmail.com", "password", "Cliente", 0);
        user.setId(1L);
        Testimonials savedTestimonial = new Testimonials(user, request.getDescription(), request.getStars());
        savedTestimonial.setId(10L);

        when(iUsersRepo.getUserById(request.getUserId())).thenReturn(user);
        when(iTestimonialsRepo.save(org.mockito.ArgumentMatchers.any(Testimonials.class))).thenReturn(savedTestimonial);

        Testimonials result = testimonialsService.create(request);

        ArgumentCaptor<Testimonials> testimonialCaptor = ArgumentCaptor.forClass(Testimonials.class);
        verify(iUsersRepo).getUserById(request.getUserId());
        verify(iTestimonialsRepo).save(testimonialCaptor.capture());

        Testimonials testimonialToSave = testimonialCaptor.getValue();
        assertNotNull(result);
        assertEquals(savedTestimonial, result);
        assertEquals(user, testimonialToSave.getUser());
        assertEquals(request.getDescription(), testimonialToSave.getDescription());
        assertEquals(request.getStars(), testimonialToSave.getStars());
    }

    @Test
    void shouldReturnTestimonialsListWithUserNames() {
        Users firstUser = new Users("primeiro@gmail.com", "password", "Primeiro Cliente", 0);
        Users secondUser = new Users("segundo@gmail.com", "password", "Segundo Cliente", 0);
        Testimonials firstTestimonial = new Testimonials(firstUser, "Excelente atendimento", 5);
        Testimonials secondTestimonial = new Testimonials(secondUser, "Muito bom", 4);

        when(iTestimonialsRepo.getAllTestimonials()).thenReturn(List.of(firstTestimonial, secondTestimonial));

        List<TestimonialListObject> result = testimonialsService.getTestimonialsList();

        assertEquals(2, result.size());
        assertEquals(5, result.get(0).getStars());
        assertEquals("Excelente atendimento", result.get(0).getDescription());
        assertEquals("Primeiro Cliente", result.get(0).getUserName());
        assertEquals(4, result.get(1).getStars());
        assertEquals("Muito bom", result.get(1).getDescription());
        assertEquals("Segundo Cliente", result.get(1).getUserName());
        verify(iTestimonialsRepo).getAllTestimonials();
    }

    @Test
    void shouldReturnEmptyListWhenRepositoryReturnsNull() {
        when(iTestimonialsRepo.getAllTestimonials()).thenReturn(null);

        List<TestimonialListObject> result = testimonialsService.getTestimonialsList();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(iTestimonialsRepo).getAllTestimonials();
    }
}
