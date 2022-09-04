package com.indir.kafka.example.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
}
