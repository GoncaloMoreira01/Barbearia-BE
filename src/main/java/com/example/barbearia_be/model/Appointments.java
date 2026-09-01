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

    // An appointment is a local business time. DATETIME does not apply a timezone conversion.
    @Column(name = "scheduleDate", columnDefinition = "DATETIME")
    private LocalDateTime scheduleDate;

    @Column(name = "description")
    private String description;

    @Column(name="serviceType")
    private Long serviceType;

    public Appointments(Long id, Users client, Users barber, LocalDateTime scheduleDate) {
        this.id = id;
        this.client = client;
        this.barber = barber;
        this.scheduleDate = scheduleDate;
    }

    public Appointments(Users client, Users barber, LocalDateTime scheduleDate, String description, Long serviceType) {
        this.client = client;
        this.barber = barber;
        this.scheduleDate = scheduleDate;
        this.description = description;
        this.serviceType = serviceType;
    }

    public Appointments() {

    }
}
