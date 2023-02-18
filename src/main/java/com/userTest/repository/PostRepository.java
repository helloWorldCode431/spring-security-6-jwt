package com.userTest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.userTest.model.Post;

@Repository
public class PostRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Post> getPostList(){
		List<Post> list = new ArrayList<Post>();
		
		String sql = "SELECT * FROM Posts;";
		SqlRowSet rs = jdbc.queryForRowSet(sql);
		
		while(rs.next()) {			
			Post post = new Post();
			post.setUserID(rs.getInt("UserID"));
			post.setTitle(rs.getString("Title"));
			post.setBody(rs.getString("Body"));
			list.add(post);
		}
		
		return list;
	}

	public int addPost(int userID, String title, String body) {
		String sql = String.format("INSERT INTO Posts(UserID, Title, Body) " +
								   "VALUES(%s, '%s', '%s');" , userID, title, body);	
		
		return jdbc.update(sql);
	}

}
