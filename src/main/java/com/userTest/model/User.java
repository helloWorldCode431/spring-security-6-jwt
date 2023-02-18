package com.userTest.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User implements UserDetails {
  private long id;
  private String username;
  private String email;
  private String password;
  private boolean enabled;
  private long dateCreated;
  private boolean verifiedEmail;
  private int reputation;
  
  private List<String> roles;
  
  public User() {
	  
  }

  public User(Long id, String username, String password, List<String> roles) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public long getDateCreated() {
	return dateCreated;
}

public void setDateCreated(long dateCreated) {
	this.dateCreated = dateCreated;
}

public boolean isVerifiedEmail() {
	return verifiedEmail;
}

public void setVerifiedEmail(boolean verifiedEmail) {
	this.verifiedEmail = verifiedEmail;
}

public int getReputation() {
	return reputation;
}

public void setReputation(int reputation) {
	this.reputation = reputation;
}
  
  

  
}
