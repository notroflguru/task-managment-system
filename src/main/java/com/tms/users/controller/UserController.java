package com.tms.users.controller;

import com.tms.users.dto.CreateUserRequest;
import com.tms.users.dto.UpdateUserRequest;
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
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody CreateUserRequest request
            ) {
        log.info("UserController: Вызван метод createUser");
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(request));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        log.info("UserController: Вызван метод findAll");
        List<User> userList = userService.findAll();
        if (userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(
            @PathVariable("id") Long id
    ) {
        log.info("UserController: Вызван метод findUserById; id = {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
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
    public ResponseEntity<User> updateUserById(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserRequest request
    ) {
        log.info("UserController: Вызван метод changeUserById; id = {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.changeUser(id, request));
    }
}
