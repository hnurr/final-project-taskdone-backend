package com.hnurceylan.finalprojecttaskdone.entities;


import com.hnurceylan.finalprojecttaskdone.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    // @ElementCollection(fetch = FetchType.EAGER)
     @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    private String name;
    private String surname;
    private String phoneNumber;
    private String country;
    private String city;
    private String district;
    private String neighborhood;
    private Boolean isCompony;
    private String companyName;
    private String serviceArea;
    private Boolean isApproved;
    private String description;



    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments; // Kullanıcının aldığı randevular

    @OneToMany(mappedBy = "provider")
    private List<Appointment> providedAppointments; // Sağlayıcı olarak verdiği randevul



}
