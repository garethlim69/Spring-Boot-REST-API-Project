package com.gareth.springbootassessment.usersystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gareth.springbootassessment.usersystem.repository.UserRepository;
import com.gareth.springbootassessment.usersystem.jwt.JwtUtil;
import com.gareth.springbootassessment.usersystem.service.MyUserDetailsService;
import com.gareth.springbootassessment.usersystem.objects.User;
import com.gareth.springbootassessment.usersystem.authentication.AuthenticationRepsonse;
import com.gareth.springbootassessment.usersystem.authentication.AuthenticationRequest;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@GetMapping("/hello")
	public String helloWorld() {
		return ("Hello World");
	}
	
	@PostMapping("/api/signup")
	public MappingJacksonValue createUser(@RequestBody User user) throws Exception {
		int num = repository.countByUsername(user.getUsername());
		if (num > 0) {
			throw new Exception("User Already Exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("dispUsername", "userId");
		FilterProvider filters = new SimpleFilterProvider().addFilter("Filter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/api/getprofile")
	public MappingJacksonValue getProfile(HttpServletRequest request) {
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtTokenUtil.extractUsername(jwt);
		}
		
		User user = repository.findByUsername(username);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username", "dispUsername", "userId");
		FilterProvider filters = new SimpleFilterProvider().addFilter("Filter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@PostMapping("/api/updatemyprofile")
	public void updateProfile(@RequestBody User user,HttpServletRequest request) {
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtTokenUtil.extractUsername(jwt);
		}
		
		String newUsername = user.getDispUsername();
		System.out.println("new username entered is: " + newUsername);
		User newUser = repository.findByUsername(username);
		newUser.setDispUsername(newUsername);
		repository.save(newUser);
	}
	
	@PostMapping("/api/session/signin")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		User user = repository.findByUsername(authenticationRequest.getUsername());
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println(user.getUserId().toString());
		return ResponseEntity.ok(new AuthenticationRepsonse(token, user.getDispUsername(), user.getUserId()));
		
	}
	
	@PostMapping("/api/session/logout")
	public void logout() {
	}
	
	@GetMapping("/test")
	public String test(){
		return ("authenticated");
	}
}