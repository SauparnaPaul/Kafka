package com.learning.spring.kafka.api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {
  
  @Bean
  public NewTopic topicWithPartition() {
    return TopicBuilder.name(KafkaConstants.TOPIC_WITH_PARTITIONS)
      .partitions(2)
      .replicas(2)
      .build();
  }
  
  @Bean
  public NewTopic testTopic() {
    return TopicBuilder.name(KafkaConstants.TOPIC_1)
      .partitions(1)
      .replicas(1)
      .build();
  }
  
  @Bean
  public NewTopic testTopic2() {
    return TopicBuilder.name(KafkaConstants.TOPIC_2)
      .partitions(1)
      .replicas(1)
      .build();
  }
}