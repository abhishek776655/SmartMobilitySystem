package com.mobility.user_service.service;

import com.mobility.user_service.dto.UserRequestDTO;
import com.mobility.user_service.dto.UserResponseDTO;
import com.mobility.user_service.model.User;

import java.util.Optional;

public interface IUserService {
    UserResponseDTO saveUser(UserRequestDTO userRequest);
    Optional<UserResponseDTO> getUserByEmail(String email);
    Optional<UserResponseDTO> getUserById(Long id);
}
