package com.mobility.authservice.exceptions.exception_handlers.Impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mobility.authservice.exceptions.exception_handlers.IStatusCodeMapper;

@Component
public class DefaultStatusCodeMapper implements IStatusCodeMapper {
    @Override
    public HttpStatus mapStatus(int statusCode, HttpStatus defaultStatus) {
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (IllegalArgumentException e) {
            return defaultStatus;
        }
    }
}
