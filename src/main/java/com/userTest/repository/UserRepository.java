package com.userTest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.userTest.model.User;
import com.userTest.model.Role;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	  
	
	public User findByUsername(String username) {
		User user = new User();
		
		String sql = String.format("SELECT * FROM Users WHERE Users.Username = '%s';", username);
		
		SqlRowSet rs = jdbc.queryForRowSet(sql);
		
		while(rs.next()) {			
		    user.setId(rs.getLong("ID"));
		    user.setUsername(rs.getString("Username"));
		    user.setEmail(rs.getString("Email"));
		    user.setPassword(rs.getString("Password"));
		    user.setEnabled(rs.getBoolean("Enabled"));
		    user.setDateCreated(rs.getLong("DateCreated"));
		    user.setVerifiedEmail(rs.getBoolean("VerifiedEmail"));		    
		    
		    user.setRoles(getRolesByUserId(rs.getLong("ID")));		    
		}
	
	    return user;
	}
	
	
	private List<String> getRolesByUserId(long id) {
		List<String> roles = new ArrayList<String>();
		String sql = String.format("SELECT Roles.RoleName AS role_name "
				+ "FROM Roles "
				+ "INNER JOIN UserRoles ON UserRoles.RoleID = Roles.ID "
				+ "WHERE UserID = %s;", id);
		
		SqlRowSet rs = jdbc.queryForRowSet(sql);
		
		while(rs.next()) {
			roles.add(rs.getString("role_name"));
		}
		
		return roles;
	}
	
	private String signup(String email, String username, String password) {
		
		return null;
	}

}
