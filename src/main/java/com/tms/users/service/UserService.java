package com.tms.users.service;

import com.tms.users.dto.CreateUserRequest;
import com.tms.users.dto.UpdateUserRequest;
import com.tms.users.mapper.UserMapper;
import com.tms.users.model.User;
import com.tms.users.model.UserEntity;
import com.tms.users.repository.UserRepository;
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

    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public User changeUser(Long id, UpdateUserRequest request) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
        if (request.getName() != null) {
            entity.setName(request.getName());
        }
        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }
        if (request.getLogin() != null) {
            entity.setLogin(request.getLogin());
        }
        if (request.getPasswordHash() != null) {
            entity.setPasswordHash(request.getPasswordHash());
        }
        if (request.getRole() != null) {
            entity.setRole(request.getRole());
        }
        return userMapper.toDomain(userRepository.save(entity));
    }
}
