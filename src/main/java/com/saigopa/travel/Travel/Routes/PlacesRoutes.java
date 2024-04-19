package com.saigopa.travel.Travel.Routes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.saigopa.travel.Travel.Models.BaseResponse;
import com.saigopa.travel.Travel.Models.Feed.PlacesDetails;
import com.saigopa.travel.Travel.Services.PlacesServices;
import com.saigopa.travel.Travel.Services.UserServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PlacesRoutes {

    @Autowired
    PlacesServices placeServices;
    
    @Autowired 
    UserServices userServices;

    @PostMapping("/addPlace")
    public BaseResponse postAddPlace(@RequestBody PlacesDetails details) {

        if (placeServices.getPlaceByName(details.getName()) != null) {
            return new BaseResponse(false, "Place name already exists");
        }

        details.setPlaceImages(placeServices.updateImageUrls(details.getName()));

        details.setCreatedAt(new Date());
        details.setUpdatedAt(new Date());
        placeServices.saveNewPlaceData(details);

        return new BaseResponse(true, "Updated");
    }

    @GetMapping("/getAllPlaces")
    public BaseResponse getAllPlaces(@RequestHeader("Authorization") String token) {
        try{
            return new BaseResponse(true,"Places",placeServices.getAllPlaces());
        }catch(Exception e){
            return new BaseResponse(false,e.getMessage());
        }
    }
    

}
