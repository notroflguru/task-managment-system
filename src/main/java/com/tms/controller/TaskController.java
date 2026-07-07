package com.tms.controller;

import com.tms.dto.ChangeTaskStatusRequest;
import com.tms.dto.CreateTaskRequest;
import com.tms.model.Task;
import com.tms.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        log.info("Вызван метод findAll()");
        List<Task> taskList = taskService.findAll();
        if (taskList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(
            @PathVariable("id") Long id
    ) {
        log.info("Вызван метод findTaskById; id = {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findTaskById(id));
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(
            @RequestBody CreateTaskRequest request
            ) {
        log.info("Вызван метод createTask");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> changeTaskStatus(
            @PathVariable Long id,
            @RequestBody ChangeTaskStatusRequest request
        ) {
        log.info("Вызван метод changeTaskStatus; id = {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.changeTaskStatus(id, request.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(
            @PathVariable("id") Long id
    ) {
        log.info("Вызван метод deleteTaskById; id = {}", id);
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}