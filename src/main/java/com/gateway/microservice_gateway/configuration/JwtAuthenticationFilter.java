package com.gateway.microservice_gateway.configuration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gateway.microservice_gateway.service.JwtService;
import com.mysql.cj.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;


	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
        final String token = getTokenFromRequest(request);
        final String username;
        try {       	
        	if (token==null)
            {
                filterChain.doFilter(request, response);
            }
            username=jwtService.getUsernameFromToken(token);
            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, userDetails))
                {
                    UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            
            filterChain.doFilter(request, response);
        }catch(Exception e) {
        	log.error("Critical error to filter chain:  "+e);
        }
        
    }

    private String getTokenFromRequest(HttpServletRequest request) {
    	final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
    	try {
            if(StringUtils.hasWildcards(authHeader) && authHeader.startsWith("Bearer "))
            {
                return authHeader.substring(7);
            }
    	}catch(Exception e) {
    		log.error("error to get token from request:   "+e);
    		return "error to exception  "+e;
    	}
    	return authHeader;
    }   

}
