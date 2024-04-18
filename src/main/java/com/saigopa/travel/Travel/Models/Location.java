package com.saigopa.travel.Travel.Models;

public class Location {
    Long latitude;
    Long longitude;
    
    public Location(Long latitude, Long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Long getLatitude() {
        return latitude;
    }
    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
    public Long getLongitude() {
        return longitude;
    }
    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
    @Override
    public String toString() {
        return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    
    
}
