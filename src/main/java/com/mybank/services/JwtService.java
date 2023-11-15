package com.mybank.services;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
