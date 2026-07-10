package com.tms.users.service;

import com.tms.users.dto.CreateUserRequest;
import com.tms.users.dto.UpdateUserRequest;
import com.tms.users.dto.UserResponse;
import com.tms.users.mapper.UserMapper;
import com.tms.users.model.UserEntity;
import com.tms.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(CreateUserRequest request) {
        UserEntity entity = userMapper.toEntity(request);
        userRepository.save(entity);
        return userMapper.toResponse(entity);
    }

    public List<UserResponse> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.toResponseList(userEntityList);
    }

    public UserResponse findUserById(Long id) {
        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with id " + id + " not found"));
        return userMapper.toResponse(entity);
    }

    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
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
        if (request.getPassword() != null) {
            entity.setPasswordHash(request.getPassword());
        }
        if (request.getRole() != null) {
            entity.setRole(request.getRole());
        }
        return userMapper.toResponse(userRepository.save(entity));
    }
}
