package com.learning.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.kafka.producer.dto.CarDTO;
import com.learning.kafka.producer.servico.CarProducer;

@RestController
@RequestMapping("/cars")
public class CarController {
	
	@Autowired
	private CarProducer carProducer;
	
	@PostMapping()
	public ResponseEntity<CarDTO> create(@RequestBody CarDTO carDTO){
		return carProducer.send(carDTO);
	}
}
