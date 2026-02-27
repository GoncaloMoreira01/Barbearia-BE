package com.example.barbearia_be.dto.appointments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
public class BarberAppointmentsRequest {
    private long BarberId;
    private LocalDate scheduleDate;
}
