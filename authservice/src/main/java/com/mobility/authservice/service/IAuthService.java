package com.mobility.authservice.service;

import com.mobility.authservice.dto.AuthResponse;
import com.mobility.authservice.dto.LoginRequest;
import com.mobility.authservice.dto.RegisterUserRequest;

public interface IAuthService {
    AuthResponse register (RegisterUserRequest request);
    AuthResponse login (LoginRequest request);
}
