package com.example.barbearia_be.controller;


import com.example.barbearia_be.dto.appointments.AppointmentInfo;
import com.example.barbearia_be.dto.appointments.BarberAppointmentsResponseDto;
import com.example.barbearia_be.dto.appointments.CreateAppointmentRequest;
import com.example.barbearia_be.dto.appointments.UpdateAppointmentRequest;
import com.example.barbearia_be.model.Appointments;
import com.example.barbearia_be.service.AppointmentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PutMapping("/updateAppointment")
    public ResponseEntity<String> updateAppointment(@RequestBody UpdateAppointmentRequest updateAppointmentRequest) {
        try {
            Appointments appointment = appointmentsService.updateAppointment(updateAppointmentRequest);
            if (appointment != null) {
                return ResponseEntity.ok("Ok");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nok");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<Boolean> deleteAppointment(@RequestParam long id) {
        try {
            boolean deleted = appointmentsService.deleteAppointment(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.CREATED).body(true);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
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

    @GetMapping("/getNextClientAppointments")
    public ResponseEntity<List<BarberAppointmentsResponseDto>> getNextClientAppointments(@RequestParam long clientId) {
        try {
            List<BarberAppointmentsResponseDto> barberAppointments = appointmentsService.getNextClientAppointments(clientId);
            if (barberAppointments != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(barberAppointments);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getOldClientAppointments")
    public ResponseEntity<List<BarberAppointmentsResponseDto>> getOldClientAppointments(@RequestParam long clientId) {
        try {
            List<BarberAppointmentsResponseDto> barberAppointments = appointmentsService.getOldClientAppointments(clientId);
            if (barberAppointments != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(barberAppointments);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAvailableDatesForBarber")
    public ResponseEntity<List<LocalDateTime>> getAvailableDatesForBarber(@RequestParam long barberId, @RequestParam LocalDate scheduleDate) {
        try {
            List<LocalDateTime> availableSlots = appointmentsService.getAvailableDatesForBarber(barberId, scheduleDate);
            if (availableSlots != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(availableSlots);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAppointmentById")
    public ResponseEntity<AppointmentInfo> getAppointmentById(@RequestParam long id) {
        try {
            AppointmentInfo appointmentInfo = appointmentsService.getAppointmentById(id);
            if (appointmentInfo != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(appointmentInfo);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
