package com.hnurceylan.finalprojecttaskdone.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Getter
//@Setter
//
//@Table(name = "customer")
//public class Customer {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String email;
//    private String password;
//    private String name;
//    private String surname;
//    private String phone;
//
//    @OneToOne(cascade = CascadeType.ALL) // CascadeType.ALL ile User'ın da otomatik kaydedilmesini sağlarsınız
//    @JoinColumn(name = "id")
//    private User user; // Provider'ın giriş bilgileri User'da tutulacak
//
//}
