package com.mobility.authservice.exceptions.exception_handlers.Impl;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobility.authservice.dto.ApiResponse;

@RestControllerAdvice
public class FeignExceptionHandler {
    private final DefaultFeignExceptionHandler exceptionHandler;

    FeignExceptionHandler(DefaultFeignExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponse<String>> handleFeignException(FeignException ex) {
        return exceptionHandler.handleFeignException(ex);
    }
}