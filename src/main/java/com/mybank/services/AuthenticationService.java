package com.mybank.services;


import org.springframework.stereotype.Service;

import com.mybank.dto.JwtAuthenticationResponse;
import com.mybank.dto.SignUpRequest;
import com.mybank.dto.SigninRequest;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

