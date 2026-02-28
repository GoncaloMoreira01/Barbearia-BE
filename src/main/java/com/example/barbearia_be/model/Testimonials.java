package com.example.barbearia_be.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "testimonials")
public class Testimonials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "description")
    private String description;

    @Column(name = "stars")
    private Integer stars;

    public Testimonials(Users user, String description, Integer stars) {
        this.user = user;
        this.description = description;
        this.stars = stars;
    }
}
