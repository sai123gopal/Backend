package com.saigopa.travel.Travel.Models.User;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saigopa.travel.Travel.Models.Location;

@Document(value = "Users")
public class UserDataModel {

    @Id
    String id;
    @Indexed(unique = true)
    String referalCode;
    @CreatedDate
    Date createdAt;
    String referedBy;
    Boolean isEmailVerified;
    String JWTToken;

    @Indexed(unique = true)
    public String email;
    public String userType;
    public String firstName;
    public String lastName;
    public String password;
    public Date birthDate;
    public Date updatedAt;
    public String deviceData;
    public String lastKnownLocationName;
    public Location userLocation;
    public String FCMToken;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getReferalCode() {
        return referalCode;
    }

    public void setReferalCode(String referalCode) {
        this.referalCode = referalCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(String deviceData) {
        this.deviceData = deviceData;
    }

    public String getReferedBy() {
        return referedBy;
    }

    public void setReferedBy(String referedBy) {
        this.referedBy = referedBy;
    }

    @Override
    public String toString() {
        return "UserDataModel [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
                + ", referalCode=" + referalCode + ", userType=" + userType + ", password=" + password + ", createdAt="
                + createdAt + ", birthDate=" + birthDate + ", updatedAt=" + updatedAt + ", deviceData=" + deviceData
                + ", referedBy=" + referedBy + "]";
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public String getJWTToken() {
        return JWTToken;
    }

    public void setJWTToken(String jWTToken) {
        JWTToken = jWTToken;
    }

    public String getLastKnownLocationName() {
        return lastKnownLocationName;
    }

    public void setLastKnownLocationName(String lastKnownLocationName) {
        this.lastKnownLocationName = lastKnownLocationName;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }

    public String getFCMToken() {
        return FCMToken;
    }

    public void setFCMToken(String fCMToken) {
        FCMToken = fCMToken;
    }



}
