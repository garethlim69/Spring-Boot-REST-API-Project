package com.gareth.springbootassessment.usersystem.objects;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
	
	@Id
	@Column(name = "car_id")
	private String carId;
	
	@Column(name = "car_name")
	private String carName;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "car")
	private List<Variance> variance;
	
	public Car() {
		super();
	}

	public Car(String carId, String carName, String brand, String description) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.brand = brand;
		this.description = description;
	}

	public String getCarId() {
		return carId;
	}

	public String getCarName() {
		return carName;
	}

	public String getBrand() {
		return brand;
	}

	public String getDescription() {
		return description;
	}

	public List<Variance> getVariance() {
		return variance;
	}
	
	
	
}