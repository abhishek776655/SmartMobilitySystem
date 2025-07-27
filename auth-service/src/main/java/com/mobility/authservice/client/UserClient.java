package com.mobility.authservice.client;

import com.mobility.authservice.dto.ApiResponse;
import com.mobility.authservice.dto.RegisterUserRequest;
import com.mobility.authservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", url = "${user.service.url}")
public interface UserClient {
    @PostMapping("/users")
    ApiResponse<UserResponseDTO> createUser(@RequestBody RegisterUserRequest userRequestDTO);

    @GetMapping("/users/email/{email}")
    ApiResponse<UserResponseDTO> getUserByEmail(@PathVariable String email);
}
