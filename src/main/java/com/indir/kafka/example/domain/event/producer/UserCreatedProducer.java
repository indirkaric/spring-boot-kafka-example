package com.indir.kafka.example.domain.event.producer;


import com.indir.kafka.example.domain.event.model.UserCreatedEvent;
import com.indir.kafka.example.domain.event.model.UserDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserCreatedProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void sendMessage(UserCreatedEvent event) {
        log.info("UserCreatedEvent has been created {}", event);
        kafkaTemplate.send("user-created-topic", event);
    }
}
