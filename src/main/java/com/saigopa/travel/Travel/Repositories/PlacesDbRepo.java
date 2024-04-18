package com.saigopa.travel.Travel.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.saigopa.travel.Travel.Models.Feed.PlacesDetails;


public interface PlacesDbRepo extends MongoRepository<PlacesDetails,String> {

    @Query("{_id : '?0'}")
    PlacesDetails findByPlaceId(String userId);

    @Query("{name : {$regex:'?0'}}")
    ArrayList<PlacesDetails> findByPlaceName(String name);


}
