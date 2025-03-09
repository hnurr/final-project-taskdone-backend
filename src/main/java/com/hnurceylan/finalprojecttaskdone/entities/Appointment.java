package com.hnurceylan.finalprojecttaskdone.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    @JsonIgnore
    private User user; // Randevuyu alacak kullanıcı (USER)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
//    @JsonBackReference
    @JsonIgnore
    private User provider; // Randevuyu veren sağlayıcı (PROVIDER)

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;


    private String userFirstName; // Kullanıcının adı
    private String userLastName;  // Kullanıcının soyadı
    private String userPhoneNumber; // Kullanıcının telefon n

}
