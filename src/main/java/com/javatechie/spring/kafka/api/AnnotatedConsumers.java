package com.javatechie.spring.kafka.api;

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

	
	//First group consumption
	/*
		@KafkaListener(groupId = "javatechie-1", topics = "javatechie", containerFactory = "kafkaListenerContainerFactory")
		public List<String> getMsgFromTopic(String data) {
			messages.add(data);
			System.out.println("Consumed the message from first:"+data);
			return messages;
		}
		*/
		
		//Second group consumption
		/*
		@KafkaListener(groupId = "javatechie-2", topics = "javatechie", containerFactory = "kafkaListenerContainerFactory")
		public List<String> getMsgFromSecondTopic(String data) {
			messages.add(data);
			System.out.println("Consumed the message from second:"+data);
			return messages;
		}
	*/
	
	//partition0
		@KafkaListener(
				  topicPartitions = @TopicPartition(
						  topic = "topicWithPartition",
						  partitions = { "0" }),
				  containerFactory = "kafkaListenerContainerFactoryWithPartitions")
		public List<String> getMsgFromTopicWithPartition0(String data) {
			messages.add(data);
			System.out.println("Consumed the message from partitions0:"+data);
			return messages;
		}
		
		//partition1
		@KafkaListener(
				  topicPartitions = @TopicPartition(
						  topic = "topicWithPartition",
						  partitions = { "1" }),
				  containerFactory = "kafkaListenerContainerFactoryWithPartitions")
		public List<String> getMsgFromTopicWithPartition1(String data) {
			messages.add(data);
			System.out.println("Consumed the message from partitions1:"+data);
			return messages;
		}
}
