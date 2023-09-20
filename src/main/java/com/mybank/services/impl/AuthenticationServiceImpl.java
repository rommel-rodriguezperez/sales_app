package com.mybank.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mybank.dao.request.SignUpRequest;
import com.mybank.dao.request.SigninRequest;
import com.mybank.dao.response.JwtAuthenticationResponse;
import com.mybank.entities.Role;
import com.mybank.entities.Employee;
import com.mybank.entities.ApplicationRole;
import com.mybank.entities.ApplicationUser;
import com.mybank.repositories.ApplicationRoleRepository;
import com.mybank.repositories.EmployeeRepository;
import com.mybank.repositories.UserRepository;
import com.mybank.services.AuthenticationService;
import com.mybank.services.JwtService;

//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    private ApplicationRoleRepository applicationRoleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			JwtService jwtService, AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        var user = ApplicationUser.builder()
//        		.firstName(request.getFirstName())
//        		.lastName(request.getLastName())
//                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
//                .role(ROLE_USER)
//                .build();

		// TODO: Maybe I have to get the role repository to get the an
		// actual Application Role from the database.
		ApplicationRole role = applicationRoleRepository.findByName("ROLE_USER");
        var user = new ApplicationUser();
		Optional<Employee> optionalEmployee = Optional.empty();


		if (request.getEmployeeId() != null)
			optionalEmployee =
					employeeRepository.findById(request.getEmployeeId());

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // TODO: Maybe this ApplicationRole and Employee Object are not serializable?
        user.setEmployee(optionalEmployee.orElse(null));
        user.setRole(role);
        user.setActive(true);

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);

		JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();
		authenticationResponse.setToken(jwt);

        return authenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);

		JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();
		authenticationResponse.setToken(jwt);

        return authenticationResponse;
    }
}

