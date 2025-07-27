package com.mobility.authservice.service;

import com.mobility.authservice.dto.AuthResponse;
import com.mobility.authservice.dto.LoginRequest;

public interface IAuthService {
    AuthResponse login (LoginRequest request);
}
