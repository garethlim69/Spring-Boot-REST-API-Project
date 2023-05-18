package com.gareth.springbootassessment.usersystem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gareth.springbootassessment.usersystem.objects.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	User findByUsername(String username);
	int countByUsername(String username);
}
