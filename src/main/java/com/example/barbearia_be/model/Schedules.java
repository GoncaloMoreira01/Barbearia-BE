package com.example.barbearia_be.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "clientId")
    private Long clientId;

    @JoinColumn(name = "barberId")
    private Long barberId;

    @Column(name = "scheduleDate")
    private LocalDateTime scheduleDate;

    public Schedules(Long id, Long clientId, Long barberId, LocalDateTime scheduleDate) {
        this.id = id;
        this.clientId = clientId;
        this.barberId = barberId;
        this.scheduleDate = scheduleDate;
    }

    public Schedules() {

    }
}
