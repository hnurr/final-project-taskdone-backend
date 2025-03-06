package com.hnurceylan.finalprojecttaskdone.services;

import com.hnurceylan.finalprojecttaskdone.dto.AppointmentDto;
import com.hnurceylan.finalprojecttaskdone.entities.Appointment;
import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.repository.AppointmentRepository;
import com.hnurceylan.finalprojecttaskdone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(AppointmentDto appointmentDto) {
        if (appointmentDto.getUserId() == null || appointmentDto.getProviderId() == null) {
            throw new RuntimeException("User ID and Provider ID must not be null.");
        }

        // Kullanıcıyı ve sağlayıcıyı buluyoruz
        User user = userRepository.findById(appointmentDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User provider = userRepository.findById(appointmentDto.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        // Randevuyu kontrol et
        Optional<Appointment> existingAppointment = appointmentRepository.findAppointmentByDateAndTime(
                appointmentDto.getAppointmentDate().toLocalDate(), appointmentDto.getAppointmentDate().toLocalTime());

        if (existingAppointment.isPresent()) {
            throw new RuntimeException("Appointment time is already booked.");
        }

        // Randevu nesnesi oluşturuluyor
        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setProvider(provider);
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate().toLocalDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentDate().toLocalTime());
        appointment.setUserFirstName(appointmentDto.getUserFirstName());
        appointment.setUserLastName(appointmentDto.getUserLastName());
        appointment.setUserPhoneNumber(appointmentDto.getUserPhoneNumber());

        // Randevu veritabanına kaydediliyor
        return appointmentRepository.save(appointment);
    }


}