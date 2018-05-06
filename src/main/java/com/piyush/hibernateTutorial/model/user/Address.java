package com.piyush.hibernateTutorial.model.user;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String pincode;
    private String street;
    private String city;

    public Address() {
    }

    public Address(String pincode, String street, String city) {
        this.pincode = pincode;
        this.street = street;
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "pincode='" + pincode + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
