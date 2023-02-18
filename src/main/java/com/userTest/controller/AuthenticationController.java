package com.userTest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userTest.config.JwtUtils;
import com.userTest.model.AuthenticationRequest;
import com.userTest.service.MyUserDetailsService;

@RestController
@RequestMapping(path="/api/auth")
public class AuthenticationController {
	
	private AuthenticationManager authenticationManager;
	private MyUserDetailsService userDetailsService;
	private JwtUtils jwtUtils;
	
	@PostMapping(path="/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
		
		if(user != null) {
			return ResponseEntity.ok(jwtUtils.generateToken(user));
		}
		
		return ResponseEntity.status(400).body("An error occurred");
		
	}
	
}
