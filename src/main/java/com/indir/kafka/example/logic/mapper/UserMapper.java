package com.indir.kafka.example.logic.mapper;

import com.indir.kafka.example.domain.dto.CreateUserDto;
import com.indir.kafka.example.domain.dto.UserDto;
import com.indir.kafka.example.domain.entity.User;
import com.indir.kafka.example.domain.event.model.UserCreatedEvent;
import com.indir.kafka.example.domain.event.model.UserDeletedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User toEntity(CreateUserDto createUserDto);
    UserCreatedEvent toUserCreatedEvent(User user);
    UserDeletedEvent toUserDeletedEvent(User user);
}
