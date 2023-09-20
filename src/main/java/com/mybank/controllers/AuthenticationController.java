package com.mybank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dao.request.SignUpRequest;
import com.mybank.dao.request.SigninRequest;
import com.mybank.dao.response.JwtAuthenticationResponse;
import com.mybank.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
public class AuthenticationController {
	@Autowired
    private AuthenticationService authenticationService;

	
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}

