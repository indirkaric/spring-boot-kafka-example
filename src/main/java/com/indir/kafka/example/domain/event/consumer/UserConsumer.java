package com.indir.kafka.example.domain.event.consumer;

import com.indir.kafka.example.domain.entity.UserEvent;
import com.indir.kafka.example.domain.enums.EventType;
import com.indir.kafka.example.domain.event.model.UserCreatedEvent;
import com.indir.kafka.example.domain.event.model.UserDeletedEvent;
import com.indir.kafka.example.domain.repository.EventRepository;
import com.indir.kafka.example.domain.repository.UserEventRepository;
import com.indir.kafka.example.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserConsumer {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final UserEventRepository userEventRepository;

    @KafkaListener(topics = "user-created-topic", groupId = "myGroup", containerFactory = "userCreatedKafkaListenerFactory")
    public void consume(UserCreatedEvent userCreatedEvent) {
        log.info("Listening UserCreatedEvent  {}", userCreatedEvent);

        var user = userRepository.findById(userCreatedEvent.getId());
        var event = eventRepository.findByName(EventType.USER_CREATED_EVENT.getDescription());
        var userEvent = new UserEvent();

        userEvent.setUser(user.get());
        userEvent.setEvent(event);

        userEventRepository.save(userEvent);
        log.info("Event saved...");

    }
    @KafkaListener(topics = "user-deleted-topic", groupId = "myGroup", containerFactory = "userDeletedKafkaListenerFactory")
    public void consume(UserDeletedEvent userDeletedEvent) {
        log.info("Listening UserDeletedEvent  {}", userDeletedEvent);

        var user = userRepository.findById(userDeletedEvent.getId());
        var event = eventRepository.findByName(EventType.USER_DELETED_EVENT.getDescription());
        var userEvent = new UserEvent();

        userEvent.setUser(user.get());
        userEvent.setEvent(event);

        userEventRepository.save(userEvent);
        log.info("Event saved...");
    }
}
