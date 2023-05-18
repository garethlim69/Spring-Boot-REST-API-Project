package com.gareth.springbootassessment.usersystem.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gareth.springbootassessment.usersystem.objects.Car;
import com.gareth.springbootassessment.usersystem.repository.CarRepository;

import org.springframework.data.domain.Pageable;

@RestController
public class CarController {
	
	@Autowired
	private CarRepository repository;
	
	@GetMapping("/api/getcarlist")
	public LinkedHashMap<String, Object> getCar(@RequestBody HashMap<String, Object> body) {
		int pageIndex = (int)body.get("pageIndex");
		pageIndex --;
		int pageSize = (int)body.get("pageSize");
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		String queryVar = ("%" + body.get("carName") + "%");
		List<Car> newCar = repository.findByCarNameLikeIgnoreCase(queryVar, pageable);
		Long totalCount = repository.countByCarNameLikeIgnoreCase(queryVar);
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("list", newCar);
		map.put("totalcount", totalCount);
		return map;
	}
}