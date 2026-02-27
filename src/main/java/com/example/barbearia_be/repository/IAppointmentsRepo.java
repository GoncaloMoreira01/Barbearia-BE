package com.example.barbearia_be.repository;

import com.example.barbearia_be.model.Appointments;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentsRepo extends JpaRepository<Appointments, Long> {

    @Lock(LockModeType.NONE)
    @Query(value = "SELECT a FROM Appointments a where a.barber.id = :barberId AND  a.scheduleDate >= :firstMinute AND a.scheduleDate < :lastMinute")
    List<Appointments> getBarberAppointmentsByDate(@Param("barberId") long barberId, @Param("firstMinute") LocalDateTime firstMinute, @Param("lastMinute") LocalDateTime lastMinute);
}
