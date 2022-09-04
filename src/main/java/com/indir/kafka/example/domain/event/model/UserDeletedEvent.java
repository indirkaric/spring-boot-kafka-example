package com.indir.kafka.example.domain.event.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDeletedEvent implements Serializable {
    private Long id;
}
