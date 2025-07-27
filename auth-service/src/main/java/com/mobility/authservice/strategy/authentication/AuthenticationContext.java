package com.mobility.authservice.strategy.authentication;

import com.mobility.authservice.dto.UserResponseDTO;
import com.mobility.authservice.enums.RoleType;
import com.mobility.authservice.strategy.AuthenticationStrategy;
import com.mobility.authservice.strategy.Impl.DriverAuthenticationStrategy;
import com.mobility.authservice.strategy.Impl.RiderAuthenticationStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthenticationContext {
    private final Map<RoleType, AuthenticationStrategy> strategyMap;

    @Autowired
    public AuthenticationContext(List<AuthenticationStrategy> strategies) {
        strategyMap = new EnumMap<>(RoleType.class);
        for (AuthenticationStrategy strategy : strategies) {
            if (strategy instanceof DriverAuthenticationStrategy) {
                strategyMap.put(RoleType.DRIVER, strategy);
            } else if (strategy instanceof RiderAuthenticationStrategy) {
                strategyMap.put(RoleType.RIDER, strategy);
            }
        }
    }

    public AuthenticationStrategy getStrategy(RoleType role) {
        return strategyMap.get(role);
    }

    public boolean authenticate(UserResponseDTO user, String rawPassword) {
        AuthenticationStrategy strategy = strategyMap.get(user.getRole());
        if (strategy == null)
            return false;
        return strategy.authenticate(user, rawPassword);
    }

}
