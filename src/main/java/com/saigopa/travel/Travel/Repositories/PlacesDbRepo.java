package com.saigopa.travel.Travel.Repositories;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.saigopa.travel.Travel.Models.Feed.PlacesDetails;


public interface PlacesDbRepo extends MongoRepository<PlacesDetails,String> {

    @Query("{_id : '?0'}")
    PlacesDetails findByPlaceId(String userId);

    @Query("{name : {$regex:'?0'}}")
    ArrayList<PlacesDetails> getAllPlacesByName(String name);

    @Query("{name : '?0'}")
    PlacesDetails findByPlaceName(String name);
}
