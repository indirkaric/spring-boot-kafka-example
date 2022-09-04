package com.indir.kafka.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigTopic {

    @Bean
    public NewTopic userCreatedTopic() {
        return TopicBuilder
                .name("user-created-topic")
                .partitions(2)
                .build();
    }

    @Bean
    public NewTopic userDeletedTopic() {
        return TopicBuilder
                .name("user-deleted-topic")
                .partitions(2)
                .build();
    }
}
