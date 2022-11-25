package com.learning.kafka.consumer.dto;

import lombok.Data;

@Data
public class CarDTO {
	private String id;
	private String model;
	private String color;
	
	CarDTO(){
		
	}

	public CarDTO(String id, String model, String color) {
		super();
		this.id = id;
		this.model = model;
		this.color = color;
	}
	
}
