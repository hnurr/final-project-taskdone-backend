package com.hnurceylan.finalprojecttaskdone.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AppointmentDto {
    private Long userId;  // User entity'deki id'yi alıyoruz
    private Long providerId; // Provider entity'sindeki id'yi alıyoruz

    private LocalDateTime appointmentDate;


    @JsonFormat(pattern = "HH:mm")  // LocalTime formatı
    private LocalTime appointmentTime;

    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;


}
