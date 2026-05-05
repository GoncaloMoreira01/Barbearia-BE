package com.example.barbearia_be.dto.appointments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BarberAppointmentsResponseDto {
    private String barberName;
    private LocalDateTime scheduleDate;
    private String description;
    private Long serviceType;

}
