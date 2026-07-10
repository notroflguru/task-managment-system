package com.tms.users.mapper;

import com.tms.users.dto.CreateUserRequest;
import com.tms.users.dto.UserResponse;
import com.tms.users.model.User;
import com.tms.users.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole());
        entity.setPassword(user.getPasswordHash());
        entity.setLogin(user.getLogin());
        return entity;
    }

    public User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getLogin(),
                entity.getPasswordHash(),
                entity.getEmail(),
                entity.getName(),
                entity.getRole()
        );
    }

    public List<User> toDomainList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public UserResponse toResponse(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.setName(entity.getName());
        response.setLogin(entity.getLogin());
        response.setEmail(entity.getEmail());
        response.setRole(entity.getRole());
        return response;
    }

    public User toDomain(CreateUserRequest request) {
        return new User(null,
                request.getLogin(),
                request.getPassword(),
                request.getEmail(),
                request.getName(),
                request.getRole());
    }

    public List<UserResponse> toResponseList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
