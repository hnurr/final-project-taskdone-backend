package com.hnurceylan.finalprojecttaskdone.controller;

import com.hnurceylan.finalprojecttaskdone.dto.AppointmentDto;
import com.hnurceylan.finalprojecttaskdone.dto.AppointmentUserDto;
import com.hnurceylan.finalprojecttaskdone.entities.Appointment;
import com.hnurceylan.finalprojecttaskdone.error.AppointmentError;
import com.hnurceylan.finalprojecttaskdone.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        // providerId veya userId null kontrolü
        if (appointmentDto.getProviderId() == null || appointmentDto.getUserId() == null) {
            // Hata mesajı döndürme
            return new ResponseEntity<>(new AppointmentError("User ID and Provider ID must not be null."), HttpStatus.BAD_REQUEST);
        }

        try {
            // Appointment nesnesi oluşturma
            Appointment appointment = appointmentService.createAppointment(appointmentDto);
            // Başarıyla oluşturulan Appointment nesnesini döndürme
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Hata durumunda ErrorResponse dönme
            return new ResponseEntity<>(new AppointmentError("Failed to create appointment: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    // providerId'ye göre randevuları almak için bir endpoint
    @GetMapping("/provider/{providerId}")
    public List<Appointment> getAppointmentsByProviderId(@PathVariable Long providerId) {
        return appointmentService.getAppointmentsByProviderId(providerId);
    }

    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<AppointmentUserDto>> getAppointmentsByUserId(@PathVariable Long userId) {
        List<AppointmentUserDto> appointments = appointmentService.getAppointmentsByUserId(userId);
        return ResponseEntity.ok(appointments);
    }
}

