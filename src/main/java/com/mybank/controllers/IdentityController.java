package com.mybank.controllers;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.dao.request.SignUpRequest;
import com.mybank.dao.request.SigninRequest;
import com.mybank.dao.response.JwtAuthenticationResponse;
import com.mybank.dto.IdentityDto;
import com.mybank.entities.ApplicationUser;
import com.mybank.entities.Employee;
import com.mybank.entities.Manager;
import com.mybank.repositories.EmployeeRepository;
import com.mybank.repositories.UserRepository;
import com.mybank.services.AuthenticationService;
import com.mybank.services.EmployeeService;
import com.mybank.services.JwtService;
import com.mybank.services.ManagerService;
import com.mybank.services.SellerService;

import io.jsonwebtoken.Jwt;

@RestController
@RequestMapping("/api/v1/")
//@RequiredArgsConstructor
public class IdentityController {

	@Autowired
    private JwtService jwtService; 

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ManagerService managerService;
	

//    @GetMapping("/me")
//    public ResponseEntity<ApplicationUser> getMyUserInfo(@AuthenticationPrincipal Jwt jwt) {
////        String username = jwt.getClaimAsString("sub");  // Assuming the username is stored in the 'sub' claim
//        String username = jwtService.extractUserName()
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(user);
//    }
    
    @GetMapping("/me")
    public ResponseEntity<IdentityDto> getMyUserInfo(@RequestHeader("Authorization") String token) {
    	IdentityDto identityDto = new IdentityDto();
        final String authHeader = token;
        final String jwt;
        final String username;
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUserName(jwt);
        Optional<ApplicationUser> optionalUser = userRepository.findByUsername(username);

        if ( optionalUser.isEmpty()) {
        	// TODO: Handle this situation
        }

		ApplicationUser user = optionalUser.get();
		
		identityDto.setUsername(user.getUsername());
		
		// NOTE: This might not work due to LAZY loading.
//		Optional<Employee> optionalEmployee =
//				employeeService.getEmployeeById(user.getEmployee().getId());
		Employee employee = user.getEmployee();

		if (employee == null) {
			return ResponseEntity.ok(identityDto);
		}
		
		identityDto.setEmployeeId(employee.getId());
		Manager manager = employee.getManager();

		if ( manager == null) {
			identityDto.setManager(true);
		}
		
		return ResponseEntity.ok(identityDto);
    } 

}

