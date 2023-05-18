package com.gareth.springbootassessment.usersystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gareth.springbootassessment.usersystem.objects.Car;

import org.springframework.data.domain.Pageable;

public interface CarRepository extends JpaRepository<Car, String>{
	Long countByCarNameLikeIgnoreCase(String queryVar);
	List<Car> findByCarNameLikeIgnoreCase(String queryVar, Pageable pageable);
}