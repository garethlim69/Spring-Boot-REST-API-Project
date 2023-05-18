package com.gareth.springbootassessment.usersystem.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "variance")
public class Variance {
	
	@Id
	@Column(name = "varId")
	private String id;
	
	@Column(name = "varName")
	private String name;
	
	@Column(name = "price")
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;
	
	public Variance() {
		super();
	}

	public Variance(String id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
}