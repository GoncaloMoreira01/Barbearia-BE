package com.example.barbearia_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Users client;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Users barber;

    @Column(name = "scheduleDate")
    private LocalDateTime scheduleDate;

    @Column(name = "description")
    private String description;

    public Appointments(Long id, Users client, Users barber, LocalDateTime scheduleDate) {
        this.id = id;
        this.client = client;
        this.barber = barber;
        this.scheduleDate = scheduleDate;
    }

    public Appointments(Users client, Users barber, LocalDateTime scheduleDate, String description) {
        this.client = client;
        this.barber = barber;
        this.scheduleDate = scheduleDate;
        this.description = description;
    }

    public Appointments() {

    }
}
