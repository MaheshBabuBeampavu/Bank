package com.exam.hct.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDetAddress {
    private String CustomerName;
    private long phone;
    private String email;
    private String country;

    private String city;
    private String addressLane;
    private long pin;


    public CustomerDetAddress(String customerName, long phone, String email, String country, String city, String addressLane, long pin) {
        CustomerName = customerName;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.city = city;
        this.addressLane = addressLane;
        this.pin = pin;
    }

    public CustomerDetAddress() {
    }
}
