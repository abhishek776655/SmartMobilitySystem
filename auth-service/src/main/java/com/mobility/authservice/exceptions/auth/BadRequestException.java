package com.mobility.authservice.exceptions.auth;

import org.springframework.http.HttpStatus;

import com.mobility.authservice.exceptions.CustomExceptionService;

public class BadRequestException extends CustomExceptionService {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
