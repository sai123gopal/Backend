package com.saigopa.travel.Travel.Models.User;

import jakarta.validation.constraints.Email;

public class SignUpModel {

    String id;
    @Email
    String email;
    String firstName;
    String lastName;
    String password;
    String deviceData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(String deviceData) {
        this.deviceData = deviceData;
    }

    @Override
    public String toString() {
        return "SignUpModel [id=" + id + ", email=" + email + ", name=" + firstName+" "+lastName + ", password=" + password
                + ", deviceData=" + deviceData + "]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
