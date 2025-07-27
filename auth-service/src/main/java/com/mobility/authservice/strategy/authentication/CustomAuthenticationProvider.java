package com.mobility.authservice.strategy.authentication;

import com.mobility.authservice.client.UserClient;
import com.mobility.authservice.dto.ApiResponse;
import com.mobility.authservice.dto.UserResponseDTO;
import com.mobility.authservice.strategy.AuthenticationStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationContext authContext;

    @Autowired
    CustomAuthenticationProvider(PasswordEncoder passwordEncoder, @Lazy UserClient userClient,
            AuthenticationContext authContext) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
        this.authContext = authContext;

    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserResponseDTO user;
        System.out.println(email);
        ApiResponse<UserResponseDTO> userResponse = userClient.getUserByEmail(email);
        user = userResponse.getData();

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid Credentials");

        }
        AuthenticationStrategy strategy = authContext.getStrategy(user.getRole());
        if (strategy == null || !strategy.authenticate(user, password)) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        return new UsernamePasswordAuthenticationToken(email, null,
                List.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
