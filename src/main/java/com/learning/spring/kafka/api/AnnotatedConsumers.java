package com.learning.spring.kafka.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

@Configuration
@EnableKafka
public class AnnotatedConsumers {

	List<String> messages = new ArrayList<>();
	User userFromTopic = null;

	// First group consumption
	@KafkaListener(groupId = "group1", topics = "testTopic", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);
		System.out.println("@KafkaListener  from first:" + data);
		return messages;
	}

	// Second group consumption
	@KafkaListener(groupId = "group2", topics = "testTopic", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromSecondTopic(String data) {
		messages.add(data);
		System.out.println("@KafkaListener from second:" + data);
		return messages;
	}

	// SerDez group consumption
	/*
	@KafkaListener(groupId = "group3", topics = "testTopic", containerFactory = "kafkaListenerContainerSerDezFactory")
	public void getMsgFromSecondSerDezTopic(User data) {
		System.out.println("@KafkaListener from SerDez:" + data.getName());
	}*/

	// partition0
	@KafkaListener(topicPartitions = @TopicPartition(topic = "topicWithPartition", partitions = {
			"0" }), containerFactory = "kafkaListenerContainerFactoryWithPartitions")
	public List<String> getMsgFromTopicWithPartition0(String data) {
		messages.add(data);
		System.out.println("@KafkaListener from partitions0:" + data);
		return messages;
	}

	// partition1
	@KafkaListener(topicPartitions = @TopicPartition(topic = "topicWithPartition", partitions = {
			"1" }), containerFactory = "kafkaListenerContainerFactoryWithPartitions")
	public List<String> getMsgFromTopicWithPartition1(String data) {
		messages.add(data);
		System.out.println("@KafkaListener from partitions1:" + data);
		return messages;
	}
}
