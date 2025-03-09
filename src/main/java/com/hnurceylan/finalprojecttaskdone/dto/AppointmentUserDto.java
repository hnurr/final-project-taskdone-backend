package com.hnurceylan.finalprojecttaskdone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentUserDto{
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    private String providerName;
    private String providerSurname;
    private String providerCategory;
    private String providerAddress;



    public AppointmentUserDto(Long id, LocalDate appointmentDate, LocalTime appointmentTime,
                          String providerName, String providerSurname, String providerCategory, String providerAddress) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.providerName = providerName;
        this.providerSurname = providerSurname;
        this.providerCategory = providerCategory;
        this.providerAddress = providerAddress;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderSurname() {
        return providerSurname;
    }

    public void setProviderSurname(String providerSurname) {
        this.providerSurname = providerSurname;
    }

    public String getProviderCategory() {
        return providerCategory;
    }

    public void setProviderCategory(String providerCategory) {
        this.providerCategory = providerCategory;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }
}
