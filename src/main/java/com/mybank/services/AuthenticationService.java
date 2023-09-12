package com.mybank.services;


import com.mybank.dao.request.SignUpRequest;
import com.mybank.dao.request.SigninRequest;
import com.mybank.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}

