package com.mobility.authservice.exceptions.exception_handlers;

import org.springframework.http.ResponseEntity;

import com.mobility.authservice.dto.ApiResponse;

import feign.FeignException;

public interface IFeignExceptionHandler {
    ResponseEntity<ApiResponse<String>> handleFeignException(FeignException ex);
}
