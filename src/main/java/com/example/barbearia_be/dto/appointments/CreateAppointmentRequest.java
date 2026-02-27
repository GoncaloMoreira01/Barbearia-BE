package com.example.barbearia_be.dto.appointments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateAppointmentRequest {
    private long clientId;
    private long barberId;
    private LocalDateTime scheduleDate;
    private String description;
}
