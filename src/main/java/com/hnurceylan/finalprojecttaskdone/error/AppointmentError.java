package com.hnurceylan.finalprojecttaskdone.error;

public class AppointmentError {

    private String message;

    public AppointmentError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
