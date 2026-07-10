package com.tms.tasks.controller;

import com.tms.tasks.dto.TaskResponse;
import com.tms.tasks.dto.UpdateTaskRequest;
import com.tms.tasks.dto.CreateTaskRequest;
import com.tms.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        log.debug("TaskController: Вызван метод findAll()");
        List<TaskResponse> taskList = taskService.findAll();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findTask(
            @PathVariable("id") Long id
    ) {
        log.debug("TaskController: Вызван метод findTaskById; id = {}", id);
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody CreateTaskRequest request
            ) {
        log.debug("TaskController: Вызван метод createTask");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest request
        ) {
        log.debug("TaskController: Вызван метод updateTask; id = {}", id);
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable("id") Long id
    ) {
        log.debug("TaskController: Вызван метод deleteTaskById; id = {}", id);
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}