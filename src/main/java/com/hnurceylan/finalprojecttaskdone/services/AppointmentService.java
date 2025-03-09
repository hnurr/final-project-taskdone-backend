package com.hnurceylan.finalprojecttaskdone.services;

import com.hnurceylan.finalprojecttaskdone.dto.AppointmentDto;
import com.hnurceylan.finalprojecttaskdone.dto.AppointmentUserDto;
import com.hnurceylan.finalprojecttaskdone.entities.Appointment;
import com.hnurceylan.finalprojecttaskdone.entities.User;
import com.hnurceylan.finalprojecttaskdone.enums.AppointmentStatus;
import com.hnurceylan.finalprojecttaskdone.repository.AppointmentRepository;
import com.hnurceylan.finalprojecttaskdone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


        appointment.setStatus(AppointmentStatus.BEKLEMEDE);
        // Randevu veritabanına kaydediliyor
        return appointmentRepository.save(appointment);
    }

    // providerId'ye göre randevuları döndüren metod
    public List<Appointment> getAppointmentsByProviderId(Long providerId) {
        return appointmentRepository.findByProviderIdAndStatus(providerId, AppointmentStatus.BEKLEMEDE);
    }



    public List<AppointmentUserDto> getAppointmentsByUserId(Long userId) {
        List<Appointment> appointments = appointmentRepository.findByUserId(userId);

        return appointments.stream().map(appointment -> new AppointmentUserDto(
                appointment.getId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getProvider().getName(),
                appointment.getProvider().getSurname(),
                appointment.getProvider().getServiceArea(),
                appointment.getProvider().getCity() + ", " + appointment.getProvider().getDistrict() + ", " + appointment.getProvider().getNeighborhood()
        )).collect(Collectors.toList());
    }

    // Randevu durumunu güncelleme metodu
    public void updateAppointmentStatus(Long appointmentId, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Durum sadece BEKLEMEDE olan randevular için değiştirilebilir
        if (appointment.getStatus() != AppointmentStatus.BEKLEMEDE) {
            throw new RuntimeException("Appointment must be in 'BEKLEMEDE' status to approve or reject.");
        }

        // Durumu güncelle
        appointment.setStatus(status);

        // Güncellenmiş randevuyu kaydet
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getApprovedAppointments() {
        return appointmentRepository.findByStatus(AppointmentStatus.ONAYLANDI);
    }

    // Randevuyu "tamamlandı" durumuna getiren metod
    public Appointment markAsCompleted(Long appointmentId) {
        // Randevuyu ID ile buluyoruz
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        // Eğer randevu bulunamazsa veya durum "onaylı" değilse, null döner
        if (appointment != null && AppointmentStatus.ONAYLANDI.equals(appointment.getStatus())) {
            appointment.setStatus(AppointmentStatus.TAMAMLANDI);  // Durumunu "tamamlandı" olarak güncelliyoruz
            return appointmentRepository.save(appointment);  // Randevuyu kaydediyoruz
        }

        return null;  // Eğer randevu bulunamazsa ya da durumu "onaylı" değilse null döner
    }

    // Tamamlanmış randevuları getir
    public List<Appointment> getCompletedAppointments() {
        return appointmentRepository.findByStatus(AppointmentStatus.TAMAMLANDI);
    }


}