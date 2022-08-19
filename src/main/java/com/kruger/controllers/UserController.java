package com.kruger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.models.User;
import com.kruger.repositories.RoleRepository;
import com.kruger.services.RoleService;
import com.kruger.services.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping
	public void createUser(@RequestBody User user) {
		
		System.out.println(userService.saveEntity(user));
		
	}

}
