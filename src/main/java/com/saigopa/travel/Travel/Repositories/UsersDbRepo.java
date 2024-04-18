package com.saigopa.travel.Travel.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.saigopa.travel.Travel.Models.User.UserDataModel;


public interface UsersDbRepo extends MongoRepository<UserDataModel,String>{
    
    @Query("{email : '?0'}")
    UserDataModel findByEmail(String email);

    @Query("{_id : '?0'}")
    UserDataModel findByuserId(String userId);

    
}
