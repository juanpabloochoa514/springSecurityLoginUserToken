package api_springsecurity.springsecurityjuanpabloochoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api_springsecurity.springsecurityjuanpabloochoa.models.AuthenticationResponse;
import api_springsecurity.springsecurityjuanpabloochoa.models.LoginRequest;
import api_springsecurity.springsecurityjuanpabloochoa.models.RegisterRequest;
import api_springsecurity.springsecurityjuanpabloochoa.service.AutherizationService;

@RestController
@RequestMapping("/sp")
public class SpringSecurityAllControllers {

	@Autowired
	private AutherizationService autherizationService;
	
	@PostMapping("/demo")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> addPerson(@RequestBody LoginRequest loginRequest) {
		try {
			//autherizationService.login(loginRequest);
			 //return ResponseEntity.ok(autherizationService.login(loginRequest));
			 return	new ResponseEntity<AuthenticationResponse>(autherizationService.login(loginRequest),HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(" bad response"),HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
		try {
//		autherizationService.register(registerRequest);
			return new ResponseEntity<AuthenticationResponse>(autherizationService.register(registerRequest),HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<AuthenticationResponse>(autherizationService.register(registerRequest),HttpStatus.BAD_REQUEST);
		}
	}

}
