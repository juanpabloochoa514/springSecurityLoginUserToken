package com.gateway.microservice_gateway.models;

public class LoginRequest {
public LoginRequest() {
		
	}
	
	public LoginRequest(String email) {
		this.email=email;
	}
	
	public LoginRequest(String email, String password) {
		this.email=email;
		this.password=password;
	}
	
	private String email;
	private String password;
	private Role role;
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
