package com.saigopa.travel.Travel.Routes;

import org.springframework.web.bind.annotation.RestController;
import com.saigopa.travel.Travel.Models.BaseResponse;
import com.saigopa.travel.Travel.Models.User.SignUpModel;
import com.saigopa.travel.Travel.Models.User.UserDataModel;
import com.saigopa.travel.Travel.Services.UserServices;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserRoutes {

    private static final int MIN_PASSWORD_LENGTH = 7;

    @Autowired
    UserServices userServices;

    @PostMapping("/signUp")
    public BaseResponse signUpUser(@Valid @RequestBody SignUpModel signUpDetails) {
        try {
            if (signUpDetails == null) {
                return new BaseResponse(false, "SignUp details are empty");
            }

            String email = signUpDetails.getEmail();
            String password = signUpDetails.getPassword();

            if (email == null || email.isEmpty()) {
                return new BaseResponse(false, "Email is empty");
            }

            if (password == null || password.isEmpty()) {
                return new BaseResponse(false, "Password is empty");
            }

            if (password.trim().length() < MIN_PASSWORD_LENGTH) {
                return new BaseResponse(false,
                        "Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
            }

            if (userServices.getUserByEmail(email) != null) {
                return new BaseResponse(false, "User already exists");
            }

            userServices.saveSignUpUserData(signUpDetails);
            return new BaseResponse(true, "SignUp successful");
        } catch (Exception e) {
            return new BaseResponse(false, "An error occurred during sign-up"+e.getMessage());
        }
    }

    @GetMapping("/login")
    public BaseResponse loginUser(@RequestParam("email") String email, @RequestParam("password") String password,@RequestParam("FCMToken") String fcmToken) {

        try {
            if (email == null || email.isEmpty()) {
                return new BaseResponse(false, "Email is empty");
            }

            if (password == null || password.isEmpty()) {
                return new BaseResponse(false, "Password is empty");
            }

            if(fcmToken == null || fcmToken.isEmpty()){
                return new BaseResponse(false, "FCMToken is empty");
            }

            UserDataModel userData = userServices.getUserByEmail(email);
            if (userData == null) {
                return new BaseResponse(false, "User doedn't exists");
            }

            if (!userData.getPassword().trim().equals(password.trim())) {
                return new BaseResponse(false, "Incorrect password");
            }

            if (!userData.getIsEmailVerified()) {
                userServices.sendVerificationEmail(userData);
                return new BaseResponse(false, "Please Verify emailID first");
            }

            userServices.saveLoginUserData(userData,fcmToken);
            return new BaseResponse(true, "Login successful", userData);
        } catch (Exception e) {
            return new BaseResponse(false, "An error occurred during Login error " + e);
        }

    }

    @GetMapping("/verifyEmail")
    public String getMethodName(@RequestParam("id") String userId) {
        try {
            userServices.updateEmailVerified(userId);
            return new String("Email verified");
        } catch (Exception e) {
            return new String(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public BaseResponse getProfile(@RequestHeader("Authorization") String bearerToken) {
        UserDataModel user;
        try {
            user = userServices.getUserDetailsFromToken(bearerToken);
        } catch (Exception e) {
            return new BaseResponse(false, e.getMessage());
        }

        if (user == null) {
            return new BaseResponse(false, "Profile not found Please login again");
        }

        return new BaseResponse(true, "Profile data", user);
    }

    @PostMapping("/updateProfile")
    public BaseResponse updateProfile(@RequestHeader("Authorization") String token,@RequestBody UserDataModel dataModel){
       try{
        UserDataModel user;
        try {
            user = userServices.getUserDetailsFromToken(token);
            if (user == null) {
                return new BaseResponse(false, "Profile not found Please login again");
            }
        } catch (Exception e) {
            return new BaseResponse(false, e.getMessage());
        }
    

        userServices.updateUserData(dataModel,user.getId());
        return new BaseResponse(true, "User details updated");
       }catch(Exception e){
        return new BaseResponse(false, "error : "+e.getMessage());

       }
    }



}
