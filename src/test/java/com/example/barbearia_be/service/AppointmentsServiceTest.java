package com.example.barbearia_be.service;

import com.example.barbearia_be.dto.appointments.BarberAppointmentsResponseDto;
import com.example.barbearia_be.dto.appointments.CreateAppointmentRequest;
import com.example.barbearia_be.model.Appointments;
import com.example.barbearia_be.model.Users;
import com.example.barbearia_be.repository.IAppointmentsRepo;
import com.example.barbearia_be.repository.IUsersRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentsServiceTest {

    @Mock
    private IAppointmentsRepo iAppointmentsRepo;

    @Mock
    private IUsersRepo iUsersRepo;

    @InjectMocks
    private AppointmentsService appointmentsService;

    @Test
    void shouldReturnBarberAppointmentsForScheduleDate() {
        LocalDate scheduleDate = LocalDate.of(2026, 8, 1);
        Users client = user("Cliente");
        Appointments appointment = appointment(client, user("Barbeiro"), scheduleDate.atTime(10, 0), "Corte", 1L);

        when(iAppointmentsRepo.getBarberAppointmentsByDate(2L, scheduleDate.atTime(9, 0), scheduleDate.atTime(20, 0)))
                .thenReturn(List.of(appointment));

        List<BarberAppointmentsResponseDto> result = appointmentsService.getBarberAppointments(2L, scheduleDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Cliente", result.get(0).getBarberName());
        assertEquals(scheduleDate.atTime(10, 0), result.get(0).getScheduleDate());
        assertEquals("Corte", result.get(0).getDescription());
        assertEquals(1L, result.get(0).getServiceType());
        verify(iAppointmentsRepo).getBarberAppointmentsByDate(2L, scheduleDate.atTime(9, 0), scheduleDate.atTime(20, 0));
    }

    @Test
    void shouldReturnNullWhenBarberAppointmentsRepositoryReturnsNull() {
        LocalDate scheduleDate = LocalDate.of(2026, 8, 1);
        when(iAppointmentsRepo.getBarberAppointmentsByDate(2L, scheduleDate.atTime(9, 0), scheduleDate.atTime(20, 0)))
                .thenReturn(null);

        assertNull(appointmentsService.getBarberAppointments(2L, scheduleDate));
    }

    @Test
    void shouldReturnAvailableHalfHourSlotsForBarber() {
        LocalDate scheduleDate = LocalDate.of(2026, 8, 1);
        Users barber = user("Barbeiro");
        List<Appointments> appointments = List.of(
                appointment(user("Cliente 1"), barber, scheduleDate.atTime(9, 30), "Corte", 1L),
                appointment(user("Cliente 2"), barber, scheduleDate.atTime(10, 30), "Barba", 2L)
        );

        when(iAppointmentsRepo.getBarberAppointmentsByDate(2L, scheduleDate.atTime(9, 0), scheduleDate.atTime(20, 0)))
                .thenReturn(appointments);

        List<LocalDateTime> result = appointmentsService.getAvailableDatesForBarber(2L, scheduleDate);

        assertEquals(20, result.size());
        assertEquals(scheduleDate.atTime(9, 0), result.get(0));
        assertEquals(scheduleDate.atTime(10, 0), result.get(1));
        assertEquals(scheduleDate.atTime(19, 30), result.get(result.size() - 1));
    }

    @Test
    void shouldCreateAppointment() {
        LocalDateTime appointmentDate = LocalDateTime.of(2026, 8, 1, 11, 0);
        CreateAppointmentRequest request = new CreateAppointmentRequest(1L, 2L, appointmentDate, "Corte e barba", 3);
        Users client = user("Cliente");
        Users barber = user("Barbeiro");
        Appointments savedAppointment = appointment(client, barber, appointmentDate, request.getDescription(), 3L);
        savedAppointment.setId(10L);

        when(iUsersRepo.getUserById(1L)).thenReturn(client);
        when(iUsersRepo.getUserById(2L)).thenReturn(barber);
        when(iAppointmentsRepo.save(any(Appointments.class))).thenReturn(savedAppointment);

        Appointments result = appointmentsService.createAppointment(request);

        ArgumentCaptor<Appointments> appointmentCaptor = ArgumentCaptor.forClass(Appointments.class);
        verify(iUsersRepo).getUserById(1L);
        verify(iUsersRepo).getUserById(2L);
        verify(iAppointmentsRepo).save(appointmentCaptor.capture());

        Appointments appointmentToSave = appointmentCaptor.getValue();
        assertEquals(savedAppointment, result);
        assertEquals(client, appointmentToSave.getClient());
        assertEquals(barber, appointmentToSave.getBarber());
        assertEquals(appointmentDate, appointmentToSave.getScheduleDate());
        assertEquals("Corte e barba", appointmentToSave.getDescription());
        assertEquals(3L, appointmentToSave.getServiceType());
    }

    @Test
    void shouldReturnNextClientAppointmentsWithClientName() {
        Users client = user("Cliente");
        Appointments appointment = appointment(client, user("Barbeiro"), LocalDateTime.of(2026, 8, 2, 10, 0), "Corte", 1L);
        when(iAppointmentsRepo.getNextClientAppointments(org.mockito.ArgumentMatchers.eq(1L), any(LocalDateTime.class)))
                .thenReturn(List.of(appointment));

        List<BarberAppointmentsResponseDto> result = appointmentsService.getNextClientAppointments(1L);

        assertEquals(1, result.size());
        assertEquals("Cliente", result.get(0).getBarberName());
        verify(iAppointmentsRepo).getNextClientAppointments(org.mockito.ArgumentMatchers.eq(1L), any(LocalDateTime.class));
    }

    @Test
    void shouldReturnOldClientAppointmentsWithBarberName() {
        Users barber = user("Barbeiro");
        Appointments appointment = appointment(user("Cliente"), barber, LocalDateTime.of(2026, 7, 1, 10, 0), "Barba", 2L);
        when(iAppointmentsRepo.getOldClientAppointments(org.mockito.ArgumentMatchers.eq(1L), any(LocalDateTime.class)))
                .thenReturn(List.of(appointment));

        List<BarberAppointmentsResponseDto> result = appointmentsService.getOldClientAppointments(1L);

        assertEquals(1, result.size());
        assertEquals("Barbeiro", result.get(0).getBarberName());
        verify(iAppointmentsRepo).getOldClientAppointments(org.mockito.ArgumentMatchers.eq(1L), any(LocalDateTime.class));
    }

    private Users user(String name) {
        return new Users(name.toLowerCase() + "@gmail.com", "password", name, 0);
    }

    private Appointments appointment(Users client, Users barber, LocalDateTime date, String description, Long serviceType) {
        return new Appointments(client, barber, date, description, serviceType);
    }
}
