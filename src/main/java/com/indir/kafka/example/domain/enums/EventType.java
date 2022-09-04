package com.indir.kafka.example.domain.enums;

import lombok.Getter;

@Getter
public enum EventType {
    USER_CREATED_EVENT(1,"User created"),
    USER_DELETED_EVENT(2,"User deleted");
    private final int id;
    private final String description;

    EventType(int id, String description) {
        this.description = description;
        this.id = id;
    }
}
