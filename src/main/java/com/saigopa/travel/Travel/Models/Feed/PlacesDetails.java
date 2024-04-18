package com.saigopa.travel.Travel.Models.Feed;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.saigopa.travel.Travel.Models.Location;

@Document(value = "places")
public class PlacesDetails {
    @Id
    String id;
    @Indexed(unique = true)
    String placeId;
    String name;
    String address;
    String country;
    String State;
    Location cordinates;
    Double rating;
    Number ratingsCount;
    Number reviewsCount;
    String description;
    ArrayList<String> tags;
    String type;
    String mapLink;
    String thumbnailImageUrl;
    String thumbnailSquareImageUrl;
    Date createdAt;
    Date updatedAt;
    String status;
    PlaceImages placeImages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public Location getCordinates() {
        return cordinates;
    }

    public void setCordinates(Location cordinates) {
        this.cordinates = cordinates;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Number getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Number ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Number getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Number reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getMapLink() {
        return mapLink;
    }

    public void setMapLink(String mapLink) {
        this.mapLink = mapLink;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getThumbnailSquareImageUrl() {
        return thumbnailSquareImageUrl;
    }

    public void setThumbnailSquareImageUrl(String thumbnailSquareImageUrl) {
        this.thumbnailSquareImageUrl = thumbnailSquareImageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public PlaceImages getPlaceImages() {
        return placeImages;
    }

    public void setPlaceImages(PlaceImages placeImages) {
        this.placeImages = placeImages;
    }

    
    

}
