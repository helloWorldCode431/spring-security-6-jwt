package com.userTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userTest.model.User;
import com.userTest.repository.UserRepository;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
    User user = userRepository.findByUsername(username);
    
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }    
        
    return user;
  }
  
  
  
}