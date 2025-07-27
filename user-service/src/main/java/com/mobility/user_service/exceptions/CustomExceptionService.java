package com.mobility.user_service.exceptions;

import org.springframework.http.HttpStatus;

public class CustomExceptionService extends RuntimeException{
    private final HttpStatus status;
    public CustomExceptionService(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
