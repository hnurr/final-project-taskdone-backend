package com.hnurceylan.finalprojecttaskdone.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Getter
//@Setter
//@Table(name = "provider")
//
//public class Provider {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    private String name;
//    private String surname;
//    private String phoneNumber;
//    private String country;
//    private String city;
//    private String district;
//    private String neighborhood;
//    private Boolean isCompony;
//    private String companyName;
//    private String serviceArea;
//
//
//    @OneToOne(cascade = CascadeType.ALL) // CascadeType.ALL ile User'ın da otomatik kaydedilmesini sağlarsınız
//    @JoinColumn(name = "id")
//    private User user; // Provider'ın giriş bilgileri User'da tutulacak
//
//}
