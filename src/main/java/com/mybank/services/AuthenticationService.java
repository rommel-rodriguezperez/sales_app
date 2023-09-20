package com.mybank.services;


import org.springframework.stereotype.Service;

import com.mybank.dao.request.SignUpRequest;
import com.mybank.dao.request.SigninRequest;
import com.mybank.dao.response.JwtAuthenticationResponse;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

