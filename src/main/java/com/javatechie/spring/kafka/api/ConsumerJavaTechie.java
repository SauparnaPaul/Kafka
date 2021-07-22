package com.javatechie.spring.kafka.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;

import com.javatechie.spring.kafka.api.config.KafkaConsumerConfig;

@Configuration
@EnableKafka
public class ConsumerJavaTechie {
	
	@Autowired
	KafkaConsumerConfig kafkaConsumerConfig;
	
	@Autowired
	KafkaListenerContainerFactory kafkaListenerContainerFactory;
	
	@Autowired
	ConsumerFactory consumerFactory;
	

	@Bean
	 public KafkaMessageListenerContainer demoListenerContainer() {
	        ContainerProperties properties = new ContainerProperties("javatechie");
	        properties.setGroupId("bean");
	        properties.setMessageListener(new MessageListener<Integer,String>() {
	            @Override
	            public void onMessage(ConsumerRecord<Integer, String> record) {
	                System.out.println("onMessage from javatechie mesaage is :"+record.value());
	            }
	        });
	 
	        return new KafkaMessageListenerContainer(consumerFactory, properties);
	    }
	
	@Bean
	 public KafkaMessageListenerContainer demoListenerContainerWithManualAck() {
	        ContainerProperties properties = new ContainerProperties("javatechie");
	        properties.setGroupId("javatechie-3");
	        properties.setAckMode(AckMode.MANUAL);
	        properties.setMessageListener(new AcknowledgingMessageListener<Integer,String>() {
	            @Override
	            public void onMessage(ConsumerRecord<Integer, String> record,Acknowledgment acknowledgment) {
	                System.out.println("onMessage from javatechie message with Manual Ack is :"+record.value());
	                acknowledgment.acknowledge();	            
	            }
	        });
	 
	        return new KafkaMessageListenerContainer(consumerFactory, properties);
	    }
}
