package com.tms.users.mapper;

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
        entity.setPasswordHash(user.getPasswordHash());
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

    public List<User> toDomainList(List<UserEntity> entities) {
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }
}
