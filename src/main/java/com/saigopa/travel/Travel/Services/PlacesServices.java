package com.saigopa.travel.Travel.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.saigopa.travel.Travel.Models.Feed.PexelsResponse;
import com.saigopa.travel.Travel.Models.Feed.PlaceImages;
import com.saigopa.travel.Travel.Models.Feed.PlacesDetails;
import com.saigopa.travel.Travel.Repositories.PlacesDbRepo;

import io.github.cdimascio.dotenv.Dotenv;

@Service
public class PlacesServices {

    @Autowired
    PlacesDbRepo placesDbRepo;

    @Autowired
    MongoTemplate mongoTemplate;


    public void saveNewPlaceData(PlacesDetails placeDetails){
        placesDbRepo.save(placeDetails);
    }

    public PlacesDetails getPlaceByName(String name){
        return placesDbRepo.findByPlaceName(name);
    }

    public List<PlacesDetails> getAllPlaces(){
        return placesDbRepo.findAll();
    }

    public PlaceImages updateImageUrls(String placeName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", Dotenv.load().get("pexels_api_key"));
        HttpEntity<Object> entity=new HttpEntity<Object>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PexelsResponse> response = restTemplate.exchange("https://api.pexels.com/v1/search?query="+placeName+"&per_page=1", 
        HttpMethod.GET, entity, PexelsResponse.class);
    
        return response.getBody().getPhotos().get(0).getSrc();
    }
    
}
