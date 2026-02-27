package com.example.barbearia_be.dto.appointments;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class BarberAppointmentsResponseDto {
    private String clientName;
    private LocalDateTime scheduleDate;
    private String description;

}
