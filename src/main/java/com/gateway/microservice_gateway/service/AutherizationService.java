package com.gateway.microservice_gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gateway.microservice_gateway.models.AuthenticationResponse;
import com.gateway.microservice_gateway.models.LoginRequest;
import com.gateway.microservice_gateway.models.RegisterRequest;
import com.gateway.microservice_gateway.models.User;
import com.gateway.microservice_gateway.repository.UserRepository;

@Service
public class AutherizationService {
private static final Logger log = LoggerFactory.getLogger(AutherizationService.class);
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResponse login(LoginRequest request) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
	        UserDetails user = userRepository.findByUsername(request.getEmail())
					.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return new AuthenticationResponse(jwtService.getToken(user));
		} catch (Exception e) {
			log.error("ERROR TO LOGIN USER:   "+e);
			return new AuthenticationResponse("Error to authenticated, report error:  " + e);
		}
	}

	public AuthenticationResponse register(RegisterRequest request) {
		try {
			User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getRole().USER);
			userRepository.save(user);
			return new AuthenticationResponse(jwtService.getToken(user));
		}catch(Exception e) {
			log.error("ERROR TO REGISTER USER:    "+e);
			return new AuthenticationResponse("Error to register to authenticated, report error:  "+e);
		}
		
	}

}
