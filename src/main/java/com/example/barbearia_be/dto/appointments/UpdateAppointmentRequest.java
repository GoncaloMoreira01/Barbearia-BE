package com.example.barbearia_be.dto.appointments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAppointmentRequest {
    private long appointmentId;
    private long barberId;
    private LocalDateTime scheduleDate;
    private String description;
    private Integer serviceType;
}
