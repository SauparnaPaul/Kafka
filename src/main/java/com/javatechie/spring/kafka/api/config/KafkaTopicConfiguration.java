package com.javatechie.spring.kafka.api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
  
  @Bean
  public NewTopic topicWithCompressionExample() {
    return TopicBuilder.name("kafka-topic-with-compression")
      .partitions(1)
      .replicas(1)
      .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
      .build();
  }
}