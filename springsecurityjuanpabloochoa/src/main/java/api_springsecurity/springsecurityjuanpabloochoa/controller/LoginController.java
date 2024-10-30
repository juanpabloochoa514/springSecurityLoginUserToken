package api_springsecurity.springsecurityjuanpabloochoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api_springsecurity.springsecurityjuanpabloochoa.models.LoginRequest;
import api_springsecurity.springsecurityjuanpabloochoa.models.RegisterRequest;
import api_springsecurity.springsecurityjuanpabloochoa.service.AutherizationService;

@RestController
@RequestMapping("/sp")
public class LoginController {

	@Autowired
	private AutherizationService autherizationService;
	
	@PostMapping("/login")
	public ResponseEntity<String> addPerson(@RequestBody LoginRequest loginRequest) {
		try {
			autherizationService.login(loginRequest);
			return ResponseEntity.status(200).body(" WELCOME ");
		} catch (Exception e) {

			return ResponseEntity.status(500).body("internal error ");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		try {
			autherizationService.register(registerRequest);
			return ResponseEntity.status(200).body(" Register ");
		} catch (Exception e) {

			return ResponseEntity.status(500).body("internal error ");
		}
	}

}
