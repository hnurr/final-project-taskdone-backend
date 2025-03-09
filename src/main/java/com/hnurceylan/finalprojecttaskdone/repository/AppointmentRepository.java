package com.hnurceylan.finalprojecttaskdone.repository;

import com.hnurceylan.finalprojecttaskdone.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND a.appointmentTime = :time")
    Optional<Appointment> findAppointmentByDateAndTime(LocalDate date, LocalTime time);

    List<Appointment> findByProviderId(Long providerId);

    List<Appointment> findByUserId(Long userId);
}
