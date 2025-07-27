package com.mobility.user_service.dto;

import com.mobility.user_service.enums.RoleType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private RoleType role;
    private String phoneNumber;
    private String password;

}
