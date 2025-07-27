package com.mobility.authservice.utils;

import com.mobility.authservice.dto.UserResponseDTO;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserResponseDTO user){
        int expirationMs = 86400000;
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role",user.getRole())
                .claim("userId",user.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.ES256,secret)
                .compact();
    }

    public Claims validateToken(String token) throws JwtException{
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody();
    }

}
