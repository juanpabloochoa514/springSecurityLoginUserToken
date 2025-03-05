package com.gateway.microservice_gateway.models;

public class AuthenticationResponse {

	public AuthenticationResponse (String token) {
		this.token=token;
	}
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
