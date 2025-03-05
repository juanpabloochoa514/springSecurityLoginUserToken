package com.gateway.microservice_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.microservice_gateway.models.AuthenticationResponse;
import com.gateway.microservice_gateway.models.LoginRequest;
import com.gateway.microservice_gateway.models.RegisterRequest;
import com.gateway.microservice_gateway.service.AutherizationService;

@RestController
@RequestMapping("/sp")
public class SpringSecurityAllControllers {
	@Autowired
	private AutherizationService autherizationService;

	@PostMapping("/demo")
	public String welcome() {
		return "Secure endpoint, welcome!  ";
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> addPerson(@RequestBody LoginRequest loginRequest) {
		try {
			return new ResponseEntity<AuthenticationResponse>(autherizationService.login(loginRequest), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(" bad response:    " + e),
					HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
		try {
			return new ResponseEntity<AuthenticationResponse>(autherizationService.register(registerRequest),
					HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<AuthenticationResponse>(
					new AuthenticationResponse(" its no posible to register user:   " + e), HttpStatus.BAD_REQUEST);
		}
	}

}
