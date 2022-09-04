package com.indir.kafka.example.domain.event.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCreatedEvent implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String organisation;
    private boolean isActive;
}
