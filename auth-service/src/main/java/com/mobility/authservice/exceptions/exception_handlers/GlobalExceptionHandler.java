package com.mobility.authservice.exceptions.exception_handlers;

import com.mobility.authservice.dto.ApiResponse;
import com.mobility.authservice.exceptions.CustomExceptionService;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptionService.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(CustomExceptionService ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>("error", ex.getMessage(), null, Instant.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                "error",
                "Validation failed",
                errors,
                Instant.now());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>("error", "Access denied", null, Instant.now()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleValidation(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>("error", ex.getMessage(), null, Instant.now()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>("error", "Invalid credentials", null, Instant.now()));
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthServiceException(AuthenticationServiceException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ApiResponse<>("error", "Authentication service unavailable", null, Instant.now()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<String>> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>("error", ex.getMessage(), null, Instant.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleOtherExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>("error", "Something went wrong: " + ex.getMessage(), null, Instant.now()));
    }

}
