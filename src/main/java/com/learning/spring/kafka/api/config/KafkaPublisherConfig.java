package com.learning.spring.kafka.api.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.learning.spring.kafka.api.customserializer.UserSerializer;


@Configuration
public class KafkaPublisherConfig {

	
	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put("group.id", "test");
		configs.put("enable.auto.commit", "false");
		configs.put("auto.commit.interval.ms", "1000");
		//configs.put("session.timeout.ms", "30000");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Object>(configs);
	}

	@Bean(name="normalTemplate")
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	
	//customized serializer/deserializer
	
	@Bean
	public ProducerFactory<String, Object> producerFactorySerDez() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put("group.id", "test");
		configs.put("enable.auto.commit", "false");
		configs.put("auto.commit.interval.ms", "1000");
		//configs.put("session.timeout.ms", "30000");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserSerializer.class);
		return new DefaultKafkaProducerFactory<String, Object>(configs);
	}

	@Bean(name="UserSerDezTemplate")
	public KafkaTemplate<String, Object> kafkaTemplateSerDez() {
		return new KafkaTemplate<>(producerFactorySerDez());
	}
	

}
