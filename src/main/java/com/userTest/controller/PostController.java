package com.userTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userTest.model.Post;
import com.userTest.repository.PostRepository;

@RestController
@RequestMapping(path="/api/post")
public class PostController {
	
	@Autowired
	PostRepository postRepository;
	
	@GetMapping(path="/getList")
	public List<Post> getList(){
		return postRepository.getPostList();
	}
	
	@PostMapping(path="/add")
	public int add(@RequestParam(name="userID", required = true)int userID,
			@RequestParam(name="title", required = true)String title,
			@RequestParam(name="body", required = true)String body) {		
		
		return postRepository.addPost(userID, title, body);
	}
	
	
}
