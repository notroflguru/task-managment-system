package com.tms.users.mapper;

import com.tms.users.dto.CreateUserRequest;
import com.tms.users.dto.UserResponse;
import com.tms.users.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponse toResponse(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.setName(entity.getName());
        response.setId(entity.getId());
        response.setLogin(entity.getLogin());
        response.setEmail(entity.getEmail());
        response.setRole(entity.getRole());
        return response;
    }

    public List<UserResponse> toResponseList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(this::toResponse).toList();
    }

    public UserEntity toEntity(CreateUserRequest request) {
        UserEntity entity = new UserEntity();
        entity.setEmail(request.getEmail());
        entity.setLogin(request.getLogin());
        entity.setName(request.getName());
        entity.setRole(request.getRole());
        return entity;
    }
}
