package com.learning.spring.kafka.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.kafka.api.config.KafkaConstants;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {

	/*
	 * @Autowired private KafkaTemplate<String, Object> template;
	 */
	
	@Autowired
	private KafkaTemplate<String, Object> normalTemplate;
	
	@Autowired
	private KafkaTemplate<String, Object> UserSerDezTemplate;
	

	List<String> messages = new ArrayList<>();
	User userFromTopic = null;
	
	// Public to Topic 1 with callbacks
	@GetMapping("/publish_to_testTopic/{name}")
	public String publishMessage(@PathVariable String name) {
		ListenableFuture<SendResult<String, Object>> future = normalTemplate.send(KafkaConstants.TOPIC_1,  name );
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			@Override
			public void onSuccess(SendResult<String, Object> result) {
				System.out.println("the message is posted successfully");
			}
			@Override
			public void onFailure(Throwable ex) {
				System.out.println("issue in posting message");
			}
		});
		return "Data published topic : "+KafkaConstants.TOPIC_1;
	}
	
	@GetMapping("/publish_to_testTopic-2/{name}")
	public String publishMessageToSecondTopic(@PathVariable String name) {
		normalTemplate.send(KafkaConstants.TOPIC_2, "Hi " + name + " Welcome to java techie");
		return "Data published to topic : "+KafkaConstants.TOPIC_2;
	}

	@GetMapping("/publish_to_topicWithPartition/{name}")
	public String publishMessageToTopicWithPartition(@PathVariable String name) {
		normalTemplate.send(KafkaConstants.TOPIC_WITH_PARTITIONS, "Hi " + name + " Welcome to java techie");
		return "Data published to topic : "+KafkaConstants.TOPIC_WITH_PARTITIONS;
	}
	
	@GetMapping("/publishJson")
	public String publishMessage() {
		User user=new User(1, "app", null);
		normalTemplate.send(KafkaConstants.TOPIC_1, user);
		return "Json Data published";
	}

	//serializer/deserializer
	@GetMapping("/publish_to_testTopic_sd")
	public String publishMessageSerDez() {
		String [] address={"a","b"};
		User user=new User(1,"one",address );
		UserSerDezTemplate.send(KafkaConstants.TOPIC_1, user);
		return "Data published topic : "+KafkaConstants.TOPIC_1;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}
}
