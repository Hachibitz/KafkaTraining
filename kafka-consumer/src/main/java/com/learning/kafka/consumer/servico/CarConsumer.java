package com.learning.kafka.consumer.servico;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learning.kafka.consumer.dto.CarDTO;

@Service
public class CarConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(CarConsumer.class);
	
	@Value(value = "${topic.name}")
	private String topic;
	
	@Value(value = "${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@KafkaListener(topics = "${topic.name}",
			groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "carKafkaListenerContainerFactory")
	public void listenTopicCar(ConsumerRecord<String, CarDTO> record) {
		log.info("Received message: " + record.partition());
		log.info("Received message: " + record.value());
	}
	
}
