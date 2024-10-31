package api_springsecurity.springsecurityjuanpabloochoa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api_springsecurity.springsecurityjuanpabloochoa.models.AuthenticationResponse;
import api_springsecurity.springsecurityjuanpabloochoa.models.LoginRequest;
import api_springsecurity.springsecurityjuanpabloochoa.models.RegisterRequest;
import api_springsecurity.springsecurityjuanpabloochoa.models.User;
import api_springsecurity.springsecurityjuanpabloochoa.repository.UserRepository;

@Service
public class AutherizationService {

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
			return new AuthenticationResponse("the error is :   " + e);
		}
	}

	public AuthenticationResponse register(RegisterRequest request) {
		User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getRole().USER);
		userRepository.save(user);
		return new AuthenticationResponse(jwtService.getToken(user));
	}
}
