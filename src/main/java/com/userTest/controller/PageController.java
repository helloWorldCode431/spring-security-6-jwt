package com.userTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.userTest.repository.PostRepository;

@Controller
public class PageController {
	
	@Autowired
	PostRepository postRepository;
			
	@GetMapping("/")
	public String home() {					
		return "index.html";
	}
	
	@GetMapping("/posts")
	public String posts() {		
		return "posts.html";
	}
		
	@GetMapping("/login")
	public String login() {					
		return "login.html";
	}
	
	@GetMapping("/signup")
	public String signup() {					
		return "signup.html";
	}
	
	@GetMapping("/addPost")
	public String addPost() {					
		return "addPost.html";
	}
}
