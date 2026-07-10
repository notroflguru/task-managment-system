package com.tms.users.controller;

import com.tms.users.dto.CreateUserRequest;
import com.tms.users.dto.UpdateUserRequest;
import com.tms.users.dto.UserResponse;
import com.tms.users.model.User;
import com.tms.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @RequestBody CreateUserRequest request
            ) {
        log.info("UserController: Вызван метод createUser");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        log.info("UserController: Вызван метод findAll");
        List<UserResponse> userResponseList = userService.findAll();
        return ResponseEntity.ok(userResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(
            @PathVariable("id") Long id
    ) {
        log.info("UserController: Вызван метод findUserById; id = {}", id);
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable("id") Long id
    ) {
        log.info("UserController: Вызван метод deleteUserById; id = {}", id);
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserRequest request
    ) {
        log.info("UserController: Вызван метод changeUserById; id = {}", id);
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
}
