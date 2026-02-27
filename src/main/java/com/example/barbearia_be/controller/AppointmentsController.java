package com.example.barbearia_be.controller;


import com.example.barbearia_be.dto.appointments.BarberAppointmentsResponseDto;
import com.example.barbearia_be.dto.appointments.CreateAppointmentRequest;
import com.example.barbearia_be.model.Appointments;
import com.example.barbearia_be.service.AppointmentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("appointments")
public class AppointmentsController {

    private AppointmentsService appointmentsService;

    @PostMapping("/createAppointment")
    public ResponseEntity<String> createAppointment(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        try {
            Appointments appointment = appointmentsService.createAppointment(createAppointmentRequest);
            if (appointment != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nok");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getBarberAppointments")
    public ResponseEntity<List<BarberAppointmentsResponseDto>> getBarberAppointments(@RequestParam long barberId, @RequestParam LocalDate scheduleDate) {
        try {
            List<BarberAppointmentsResponseDto> barberAppointments = appointmentsService.getBarberAppointments(barberId, scheduleDate);
            if (barberAppointments != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(barberAppointments);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
