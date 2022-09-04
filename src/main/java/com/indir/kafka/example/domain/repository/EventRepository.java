package com.indir.kafka.example.domain.repository;

import com.indir.kafka.example.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByName(String name);
}
