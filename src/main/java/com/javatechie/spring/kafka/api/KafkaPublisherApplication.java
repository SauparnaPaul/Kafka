package com.javatechie.spring.kafka.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "javatechie";
	private String topic2 = "javatechie2";
	List<String> messages = new ArrayList<>();
	User userFromTopic = null;


	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		ListenableFuture<SendResult<String, Object>> future = template.send(topic,  name );
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
		return "Data published";
	}
	
	@GetMapping("/publish2/{name}")
	public String publishMessageToSecondTopic(@PathVariable String name) {
		template.send(topic2, "Hi " + name + " Welcome to java techie");
		return "Data published";
	}

	@GetMapping("/publishJson")
	public String publishMessage() {
		User user=new User(1, "app", null);
		template.send(topic, user);
		return "Json Data published";
	}

	
	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}
}
