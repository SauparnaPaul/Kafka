package com.learning.spring.kafka.api;

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

import com.learning.spring.kafka.api.config.KafkaConstants;
import com.learning.spring.kafka.api.config.KafkaConsumerConfig;

@Configuration
@EnableKafka
public class ConsumerJavaTechie {
	
	@Autowired
	KafkaConsumerConfig kafkaConsumerConfig;
	
	@Autowired
	KafkaListenerContainerFactory kafkaListenerContainerFactory;
	
	@Autowired
	ConsumerFactory consumerFactory;
	
	@Autowired
	ConsumerFactory consumerFactoryWithPartitions;
	

	@Bean
	 public KafkaMessageListenerContainer demoListenerContainer() {
	        ContainerProperties properties = new ContainerProperties(KafkaConstants.TOPIC_1);
	        properties.setGroupId("groupOnMessage1");
	        properties.setMessageListener(new MessageListener<Integer,String>() {
	            @Override
	            public void onMessage(ConsumerRecord<Integer, String> record) {
	                System.out.println("onMessage is :"+record.value());
	            }
	        });
	 
	        return new KafkaMessageListenerContainer(consumerFactory, properties);
	    }
	
	
	@Bean
	 public KafkaMessageListenerContainer demoListenerContainerWithManualAck() {
	        ContainerProperties properties = new ContainerProperties(KafkaConstants.TOPIC_1);
	        properties.setGroupId("groupOnMessage2");
	        properties.setAckMode(AckMode.MANUAL);
	        properties.setMessageListener(new AcknowledgingMessageListener<Integer,String>() {
	            @Override
	            public void onMessage(ConsumerRecord<Integer, String> record,Acknowledgment acknowledgment) {
	                System.out.println("onMessage with Manual Ack is :"+record.value());
	                acknowledgment.acknowledge();	            
	            }
	        });
	 
	        return new KafkaMessageListenerContainer(consumerFactory, properties);
	    }
	
	
	/*
	//Partitoned consumers:
	//Consumer for partition 0:
	@Bean
	 public KafkaMessageListenerContainer demoListenerContainerWithPartitions0() {
			TopicPartitionOffset topicPartitionOffset=new TopicPartitionOffset("topicWithPartition",0);
	        ContainerProperties properties = new ContainerProperties(topicPartitionOffset);
	        properties.setGroupId("groupOfPartitions");
	        properties.setMessageListener(new MessageListener<Integer,String>() {
	            @Override
	            public void onMessage(ConsumerRecord<Integer, String> record) {
	                System.out.println("onMessage from partition 0 :"+record.value());
	            }
	        });
	 
	        return new KafkaMessageListenerContainer(consumerFactoryWithPartitions, properties);
	    }
	
	//Consumer for partition 1:
		@Bean
		 public KafkaMessageListenerContainer demoListenerContainerWithPartitions1() {
				TopicPartitionOffset topicPartitionOffset=new TopicPartitionOffset("topicWithPartition",1);
		        ContainerProperties properties = new ContainerProperties(topicPartitionOffset);
		        properties.setGroupId("groupOfPartitions");

		        properties.setMessageListener(new MessageListener<Integer,String>() {
		            @Override
		            public void onMessage(ConsumerRecord<Integer, String> record) {
		                System.out.println("onMessage from partition 1 :"+record.value());
		            }
		        });
		 
		        return new KafkaMessageListenerContainer(consumerFactoryWithPartitions, properties);
		    }
		 */
}
