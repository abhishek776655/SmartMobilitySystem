package com.mobility.authservice.service.Impl;

import com.mobility.authservice.client.UserClient;
import com.mobility.authservice.dto.*;
import com.mobility.authservice.service.IAuthService;
import com.mobility.authservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    public final UserClient userClient;
    public final AuthenticationManager authenticationManager;
    public final JwtUtils jwtUtils;
    @Autowired
    public AuthService(UserClient userClient, AuthenticationManager authenticationManager, JwtUtils jwtUtils){
        this.userClient = userClient;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        ApiResponse<UserResponseDTO> userResponse = userClient.getUserByEmail(request.getEmail());
        UserResponseDTO user = userResponse.getData();
        String token = jwtUtils.generateToken(user);
        return new AuthResponse(token,user.getId(),user.getRole().toString(),user.getEmail());
    }
}
