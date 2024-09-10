package com.saigopa.travel.Travel.Routes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.saigopa.travel.Travel.Models.BaseResponse;
import com.saigopa.travel.Travel.Models.OrderRequest;
import com.saigopa.travel.Travel.Models.PlaceOrderResponse;
import com.saigopa.travel.Travel.Models.Feed.PlacesDetails;
import com.saigopa.travel.Travel.Services.PlacesServices;
import com.saigopa.travel.Travel.Services.UserServices;

import org.json.*;

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

    @Value("${rp_key}")
    public String apiKey;

    @Value("${rp_pass}")
    public String apiPass;

    @PostMapping("/addPlace")
    public BaseResponse postAddPlace(@RequestBody PlacesDetails details) {
        try{
            if (placeServices.getPlaceByName(details.getName()) != null) {
                return new BaseResponse(false, "Place name already exists");
            }
    
            details.setPlaceImages(placeServices.updateImageUrls(details.getName()));
    
            details.setCreatedAt(new Date());
            details.setUpdatedAt(new Date());
            placeServices.saveNewPlaceData(details);
    
            return new BaseResponse(true, "Updated");
        }catch(Exception e){
            return new BaseResponse(false, "Error : "+e.getMessage());
        }
    }

    @GetMapping("/getAllPlaces")
    public BaseResponse getAllPlaces(@RequestHeader("Authorization") String token) {
        try{
            return new BaseResponse(true,"Places",placeServices.getAllPlaces());
        }catch(Exception e){
            return new BaseResponse(false,e.getMessage());
        }
    }


     @PostMapping("/createOrder")
    public PlaceOrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Create the request body JSON
            JSONObject requestBody = new JSONObject();
            requestBody.put("amount", orderRequest.getAmount() * 100);
            requestBody.put("currency", orderRequest.getCurrency());
            requestBody.put("receipt", orderRequest.getReceipt());

            // Set the headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(apiKey, apiPass);  

            // Create the HttpEntity with headers and body
            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

            // Create the RestTemplate object and call the Razorpay API
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://api.razorpay.com/v1/orders", 
                    HttpMethod.POST, 
                    entity, 
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return handleSuccessResponse(response.getBody());
            } else {
                return handleError(response);
            }

        } catch (Exception e) {
            return handleError(null);
        }
    }
    

    private PlaceOrderResponse handleSuccessResponse(String responseBody) {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);

            PlaceOrderResponse.Success success = new PlaceOrderResponse.Success();
            success.setAmount(jsonResponse.optInt("amount", 0));
            success.setAmountDue(jsonResponse.optInt("amount_due", 0));
            success.setAmountPaid(jsonResponse.optInt("amount_paid", 0));
            success.setAttempts(jsonResponse.optInt("attempts", 0));
            success.setCreatedAt(jsonResponse.optInt("created_at", 0));
            success.setCurrency(jsonResponse.optString("currency", ""));
            success.setEntity(jsonResponse.optString("entity", ""));
            success.setId(jsonResponse.optString("id", ""));
            //success.setNotes(new ArrayList<>(jsonResponse.optJSONArray("notes") != null ? jsonResponse.getJSONArray("notes").toList() : new ArrayList<>()));
            success.setOfferId(jsonResponse.optString("offer_id", null));
            success.setReceipt(jsonResponse.optString("receipt", ""));
            success.setStatus(jsonResponse.optString("status", ""));

            return new PlaceOrderResponse(true, success, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new PlaceOrderResponse(false, null,null);
        }
    }

    private PlaceOrderResponse handleError(ResponseEntity<String> response) {
        PlaceOrderResponse.Failure.ErrorDetails errorDetails = new PlaceOrderResponse.Failure.ErrorDetails();

        if (response != null && response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            try {
                JSONObject responseBody = new JSONObject(response.getBody()).getJSONObject("error");

                errorDetails.setCode(responseBody.optString("code", "UNKNOWN_CODE"));
                errorDetails.setDescription(responseBody.optString("description", "Unknown error description"));
                errorDetails.setSource(responseBody.optString("source", "unknown"));
                errorDetails.setStep(responseBody.optString("step", "unknown"));
                errorDetails.setReason(responseBody.optString("reason", "unknown"));
                errorDetails.setField(responseBody.optString("field", null));
                errorDetails.setMetadata(new PlaceOrderResponse.Failure.ErrorDetails.Metadata());
            } catch (Exception e) {
                e.printStackTrace();
                errorDetails.setDescription("Error parsing error response: " + e.getMessage());
            }
        } else {
            errorDetails.setDescription("Unknown error occurred.");
        }

        PlaceOrderResponse.Failure failureResponse = new PlaceOrderResponse.Failure();
        failureResponse.setError(errorDetails);

        return new PlaceOrderResponse(false, null, failureResponse);
    }
    

}
