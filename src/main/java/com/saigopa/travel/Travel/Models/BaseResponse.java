package com.saigopa.travel.Travel.Models;

public class BaseResponse {
    Boolean status;
    String message;
    Object data = null;
    

    public BaseResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    

    public BaseResponse(Boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }



    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public Object getData() {
        return data;
    }



    public void setData(Object data) {
        this.data = data;
    }



}
