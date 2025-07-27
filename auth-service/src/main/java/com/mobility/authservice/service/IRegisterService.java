package com.mobility.authservice.service;

import com.mobility.authservice.dto.RegisterUserRequest;
import com.mobility.authservice.dto.UserResponseDTO;

public interface IRegisterService {
    UserResponseDTO register (RegisterUserRequest request);
}
