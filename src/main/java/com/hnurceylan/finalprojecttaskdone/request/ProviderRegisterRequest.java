package com.hnurceylan.finalprojecttaskdone.request;

import lombok.Data;

@Data
public class ProviderRegisterRequest {

    private String email;
    private String password;
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
}
