package com.hnurceylan.finalprojecttaskdone.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderCreateProfileDto {

    private Long id;
    private String name;
    private String surname;
    private String city;
    private String district;
    private String neigborhood;
    private String description;
    private String phoneNumber;
    private String companyName;
    private String serviceArea;


    public ProviderCreateProfileDto(String name, String surname, String city, String district,String neigborhood, String description, String phoneNumber, String companyName,String serviceArea) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.district = district;
        this.neigborhood = neigborhood;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.serviceArea=serviceArea;
    }



    public void setNeighborhood(String neighborhood) {
        this.neigborhood=neighborhood;
    }
}
