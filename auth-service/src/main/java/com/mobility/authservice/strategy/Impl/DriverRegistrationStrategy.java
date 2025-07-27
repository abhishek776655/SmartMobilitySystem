package com.mobility.authservice.strategy.Impl;

import com.mobility.authservice.client.UserClient;
import com.mobility.authservice.dto.ApiResponse;
import com.mobility.authservice.dto.DriverRegisterRequest;
import com.mobility.authservice.dto.UserResponseDTO;
import com.mobility.authservice.strategy.RegistrationStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DriverRegistrationStrategy implements RegistrationStrategy<DriverRegisterRequest> {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DriverRegistrationStrategy(PasswordEncoder passwordEncoder, UserClient userClient) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO register(DriverRegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        ApiResponse<UserResponseDTO> userResponse = userClient.createUser(request);
        UserResponseDTO user = userResponse.getData();
        // userClient.createDRider(user.getId(), request);
        return user;
    }
}