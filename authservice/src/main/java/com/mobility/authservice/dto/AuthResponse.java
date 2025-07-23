package com.mobility.authservice.dto;

public class AuthResponse {
    private String token;
    private String message;
    private String userId;
    private String role;

    // Constructor
    public AuthResponse(String token, String message, String userId, String role) {
        this.token = token;
        this.message = message;
        this.userId = userId;
        this.role = role;
    }

    // Getters and setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
