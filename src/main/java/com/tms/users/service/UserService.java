package com.tms.users.service;

import com.tms.users.dto.CreateUserRequest;
import com.tms.users.dto.UpdateUserRequest;
import com.tms.users.dto.UserResponse;
import com.tms.users.exception.UserNotFoundException;
import com.tms.users.mapper.UserMapper;
import com.tms.users.model.UserEntity;
import com.tms.users.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private UserEntity findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(CreateUserRequest request) {
        UserEntity entity = userMapper.toEntity(request);
        entity.setPasswordHash(encoder.encode(request.getPassword()));
        userRepository.save(entity);
        return userMapper.toResponse(entity);
    }

    public List<UserResponse> findAll() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userMapper.toResponseList(userEntityList);
    }

    public UserResponse findUserById(Long id) {
        UserEntity entity = findUser(id);
        return userMapper.toResponse(entity);
    }

    public void deleteUserById(Long id) {
        UserEntity entity = findUser(id);
        userRepository.delete(entity);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        UserEntity entity = findUser(id);
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
