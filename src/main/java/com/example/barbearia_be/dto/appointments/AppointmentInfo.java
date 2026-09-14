package com.example.barbearia_be.dto.appointments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentInfo {
    private Long appointmentId;
    private Long barberId;
    private LocalDateTime scheduleDate;
    private String description;
    private Long serviceType;
}
