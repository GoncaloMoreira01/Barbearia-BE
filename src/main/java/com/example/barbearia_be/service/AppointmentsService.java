package com.example.barbearia_be.service;

import com.example.barbearia_be.dto.appointments.BarberAppointmentsResponseDto;
import com.example.barbearia_be.dto.appointments.CreateAppointmentRequest;
import com.example.barbearia_be.model.Appointments;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IAppointmentsRepo;
import com.example.barbearia_be.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentsService {

    private final IAppointmentsRepo iAppointmentsRepo;
    private final IUsersRepo iUsersRepo;

    @Transactional
    public List<BarberAppointmentsResponseDto> getBarberAppointments(long barberId, LocalDate scheduleDate) {
        List<Appointments> barberAppointments = iAppointmentsRepo.getBarberAppointmentsByDate(barberId,
                scheduleDate.atTime(9, 0, 0), scheduleDate.atTime(20,0,0));
        if (barberAppointments != null) {
            List<BarberAppointmentsResponseDto> appointmentsDtoList = new ArrayList<>();
            for (Appointments appointment : barberAppointments) {
                BarberAppointmentsResponseDto appointmentDto = new BarberAppointmentsResponseDto(appointment.getClient().getName(), appointment.getScheduleDate(),
                        appointment.getDescription());
                appointmentsDtoList.add(appointmentDto);
            }
            return appointmentsDtoList;
        }
        return null;
    }

    @Transactional
    public List<LocalDateTime> getAvailableDatesForBarber(long barberId, LocalDate scheduleDate) {
        LocalDateTime scheduleDateStart = scheduleDate.atTime(9,0);
        LocalDateTime scheduleDateEnd = scheduleDate.atTime(20, 0);
        List<LocalDateTime> availableSlots = new ArrayList<>();

        List<Appointments> barberAppointments = iAppointmentsRepo.getBarberAppointmentsByDate(barberId, scheduleDateStart, scheduleDateEnd);

        LocalDateTime schedule = scheduleDateStart;
        while (!schedule.equals(scheduleDateEnd)) {
            LocalDateTime slot = schedule;
            boolean exists = barberAppointments.stream().anyMatch(a -> a.getScheduleDate().equals(slot));
            if (!exists) availableSlots.add(slot);

            schedule = schedule.plusMinutes(30);
        }
    return availableSlots;
    }

    @Transactional
    public Appointments createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        Users client = iUsersRepo.getUserById(createAppointmentRequest.getClientId());
        Users barber = iUsersRepo.getUserById(createAppointmentRequest.getBarberId());

        Appointments appointment = new Appointments(client, barber, createAppointmentRequest.getScheduleDate(), createAppointmentRequest.getDescription());
        return iAppointmentsRepo.save(appointment);
    }
}
