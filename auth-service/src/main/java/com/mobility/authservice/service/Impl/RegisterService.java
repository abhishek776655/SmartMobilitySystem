package com.mobility.authservice.service.Impl;

import com.mobility.authservice.dto.DriverRegisterRequest;
import com.mobility.authservice.dto.RegisterUserRequest;
import com.mobility.authservice.dto.RiderRegisterRequest;
import com.mobility.authservice.dto.UserResponseDTO;
import com.mobility.authservice.enums.RoleType;
import com.mobility.authservice.service.IRegisterService;
import com.mobility.authservice.strategy.Impl.DriverRegistrationStrategy;
import com.mobility.authservice.strategy.Impl.RiderRegistrationStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {

    @Autowired
    private RiderRegistrationStrategy riderRegistrationStrategy;

    @Autowired
    private DriverRegistrationStrategy driverRegistrationStrategy;

    @Override
    public UserResponseDTO register(RegisterUserRequest request) {
        if (request.getRole().equalsIgnoreCase(RoleType.RIDER.toString())) {
            RiderRegisterRequest riderRequest = new RiderRegisterRequest();
            copyCommonFields(request, riderRequest);
            return riderRegistrationStrategy.register(riderRequest);
        } else if (request.getRole().equalsIgnoreCase(RoleType.DRIVER.toString())) {
            DriverRegisterRequest driverRequest = new DriverRegisterRequest();
            copyCommonFields(request, driverRequest);
            return driverRegistrationStrategy.register(driverRequest);
        } else {
            throw new IllegalArgumentException("Unsupported role for registration: " + request.getRole());
        }
    }

    private void copyCommonFields(RegisterUserRequest source, RegisterUserRequest target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setRole(source.getRole());
    }
}
