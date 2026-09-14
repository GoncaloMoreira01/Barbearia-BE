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
    private Long id;
    private String barberName;
    private LocalDateTime scheduleDate;
    private String description;
    private Long serviceType;

    public BarberAppointmentsResponseDto(String barberName, LocalDateTime scheduleDate, String description, Long serviceType) {
        this.barberName = barberName;
        this.scheduleDate = scheduleDate;
        this.description = description;
        this.serviceType = serviceType;
    }
}
