package com.tms.service;

import com.tms.dto.CreateUserRequest;
import com.tms.mapper.UserMapper;
import com.tms.model.User;
import com.tms.model.UserEntity;
import com.tms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User createUser(CreateUserRequest request) {
        User newUser = new User(null, request.getLogin(), request.getPasswordHash(), request.getEmail(), request.getName(), request.getRole());
        UserEntity entity = userRepository.save(userMapper.toEntity(newUser));
        User resUser = userMapper.toDomain(entity);
        return resUser;
    }

    public List<User> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.toDomainList(userEntityList);
    }

    public User findUserById(Long id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
        return userMapper.toDomain(entity);
    }
}
