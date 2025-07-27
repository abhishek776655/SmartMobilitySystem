package com.mobility.authservice.exceptions.auth;

import org.springframework.http.HttpStatus;

import com.mobility.authservice.exceptions.CustomExceptionService;

public class UnauthorizedException extends CustomExceptionService {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}