package com.saigopa.travel.Travel.Routes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.saigopa.travel.Travel.Models.BaseResponse;
import com.saigopa.travel.Travel.Models.Feed.PlacesDetails;
import com.saigopa.travel.Travel.Services.PlacesServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PlacesRoutes {

    @Autowired
    PlacesServices placeServices;

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

}
