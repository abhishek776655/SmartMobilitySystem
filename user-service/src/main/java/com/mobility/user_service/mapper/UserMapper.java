package com.mobility.user_service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobility.user_service.dto.UserResponseDTO;
import com.mobility.user_service.dto.UserRequestDTO;
import com.mobility.user_service.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ObjectMapper objectMapper;

    public UserMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public User toEntity(UserRequestDTO dto) {
        return objectMapper.convertValue(dto, User.class);
    }

    public UserResponseDTO toResponseDTO(User user) {
        return objectMapper.convertValue(user, UserResponseDTO.class);

    }

    public UserResponseDTO toDTO(User user) {
        return objectMapper.convertValue(user, UserResponseDTO.class);
    }

}