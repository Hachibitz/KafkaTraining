package com.learning.kafka.producer.servico;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.learning.kafka.producer.dto.CarDTO;

@Service
public class CarProducer {
	private static final Logger logger = LoggerFactory.getLogger(CarProducer.class);
	private final String topic;
	private final KafkaTemplate<String, CarDTO> kafkaTemplate;

	public CarProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, CarDTO> kafkaTemplate) {
		super();
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public ResponseEntity<CarDTO> send(CarDTO carDTO) {
		CarDTO car = CarDTO.builder()
				.id(UUID.randomUUID().toString())
				.color(carDTO.getColor())
				.model(carDTO.getModel()).build();
		
		kafkaTemplate.send(topic, car).addCallback(
				success -> logger.info("Message send" + success.getProducerRecord().value()),
				failure -> logger.info("Message failure" + failure.getMessage())
		);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(car);
	}
}
