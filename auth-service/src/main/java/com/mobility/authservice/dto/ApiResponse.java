package com.mobility.authservice.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ApiResponse<T> {
    private String status;     // "success" or "error"
    private String message;
    private T data;
    private Instant timestamp;

    public ApiResponse(String status, String message, T data, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("success", message, data, Instant.now());
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>("error", message, data, Instant.now());
    }

}