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
public class UserDeletedProducer {

    private final KafkaTemplate<String, UserDeletedEvent> kafkaTemplate;

    public void sendMessage(UserDeletedEvent event) {
        log.info("UserDeletedEvent has been created {}", event);
        kafkaTemplate.send("user-deleted-topic", event);
    }
}
