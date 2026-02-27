package com.example.barbearia_be.service;

import com.example.barbearia_be.dto.appointments.BarberAppointmentsRequest;
import com.example.barbearia_be.dto.appointments.BarberAppointmentsResponseDto;
import com.example.barbearia_be.dto.appointments.CreateAppointmentRequest;
import com.example.barbearia_be.model.Appointments;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IAppointmentsRepo;
import com.example.barbearia_be.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentsService {

    private final IAppointmentsRepo iAppointmentsRepo;
    private final IUsersRepo iUsersRepo;

    @Transactional
    public List<BarberAppointmentsResponseDto> getBarberAppointments(BarberAppointmentsRequest barberAppointmentsRequest) {
        List<Appointments> barberAppointments = iAppointmentsRepo.getBarberAppointmentsByDate(barberAppointmentsRequest.getBarberId(),
                barberAppointmentsRequest.getScheduleDate().atStartOfDay(), barberAppointmentsRequest.getScheduleDate().atTime(23,59,59));
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
    public Appointments createAppointment(CreateAppointmentRequest createAppointmentRequest) {
        Users client = iUsersRepo.getUserById(createAppointmentRequest.getClientId());
        Users barber = iUsersRepo.getUserById(createAppointmentRequest.getBarberId());

        Appointments appointment = new Appointments(client, barber, createAppointmentRequest.getScheduleDate(), createAppointmentRequest.getDescription());
        return iAppointmentsRepo.save(appointment);
    }
}
