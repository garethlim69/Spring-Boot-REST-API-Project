package com.gareth.springbootassessment.usersystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gareth.springbootassessment.usersystem.repository.UserRepository;
import com.gareth.springbootassessment.usersystem.userdetails.MyUserDetails;
import com.gareth.springbootassessment.usersystem.objects.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		return new MyUserDetails(user);
	}
	
}