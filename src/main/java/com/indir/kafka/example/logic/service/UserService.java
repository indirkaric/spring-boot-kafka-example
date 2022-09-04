package com.indir.kafka.example.logic.service;

import com.indir.kafka.example.domain.dto.CreateUserDto;
import com.indir.kafka.example.domain.dto.UserDto;
import com.indir.kafka.example.domain.entity.User;
import com.indir.kafka.example.domain.event.producer.UserCreatedProducer;
import com.indir.kafka.example.domain.event.producer.UserDeletedProducer;
import com.indir.kafka.example.exception.error.BadRequestException;
import com.indir.kafka.example.exception.error.NotFoundException;
import com.indir.kafka.example.exception.error.RestApiError;
import com.indir.kafka.example.logic.mapper.UserMapper;
import com.indir.kafka.example.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserCreatedProducer userCreatedProducer;
    private final UserDeletedProducer userDeletedProducer;

    public List<UserDto> getAllUsers() {
        return userRepository.findAllUsers()
                .stream()
                .map(UserMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void createUser(CreateUserDto createUserDto) {
        checkIfUserExists(createUserDto.getUsername());
        var user = userRepository.save(UserMapper.INSTANCE.toEntity(createUserDto));
        userCreatedProducer.sendMessage(UserMapper.INSTANCE.toUserCreatedEvent(user));
    }

    public void deleteUser(Long id) {
        var user = findById(id);
        user.setActive(false);
        userRepository.save(user);
        userDeletedProducer.sendMessage(UserMapper.INSTANCE.toUserDeletedEvent(user));
    }

    public UserDto findUserById(Long id) {
        return UserMapper.INSTANCE.toDto(findById(id));
    }

     private User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new NotFoundException(RestApiError.USER_NOT_FOUND);
        return user.get();
    }

    private void checkIfUserExists(String username) {
        if (userRepository.findByUsername(username) != null)
            throw new BadRequestException(RestApiError.USER_ALREADY_EXIST);
    }

}
