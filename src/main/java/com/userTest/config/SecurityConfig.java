package com.userTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Repository;

import com.userTest.service.MyUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
			
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{		
		http
			.csrf().disable()			
			.authorizeHttpRequests()
				.requestMatchers("/", "/posts", "/login", "/api/auth/authenticate", "/signup", "/api/post/getList", "/styles.css", "/script.js")
				.permitAll()
				.and()	      
	        .authorizeHttpRequests()
	        	.requestMatchers("/addPost").authenticated()
	        	.requestMatchers(HttpMethod.POST, "/api/v1/post/add").authenticated()
	        .and()
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .authenticationManager(authenticationManager())			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();		
	}

	
	@Bean
	public AuthenticationManager authenticationManager(){
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return new ProviderManager(authProvider);
	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {		
		return NoOpPasswordEncoder.getInstance();
	}

	
	
	  
}
