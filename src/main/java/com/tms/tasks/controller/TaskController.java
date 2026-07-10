package com.tms.tasks.controller;

import com.tms.tasks.dto.TaskResponse;
import com.tms.tasks.dto.UpdateTaskRequest;
import com.tms.tasks.dto.CreateTaskRequest;
import com.tms.tasks.model.Task;
import com.tms.tasks.service.TaskService;
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
    public ResponseEntity<List<TaskResponse>> findAll() {
        log.info("TaskController: Вызван метод findAll()");
        List<TaskResponse> taskList = taskService.findAll();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findTaskById(
            @PathVariable("id") Long id
    ) {
        log.info("TaskController: Вызван метод findTaskById; id = {}", id);
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping()
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody CreateTaskRequest request
            ) {
        log.info("TaskController: Вызван метод createTask");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest request
        ) {
        log.info("TaskController: Вызван метод updateTask; id = {}", id);
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(
            @PathVariable("id") Long id
    ) {
        log.info("TaskController: Вызван метод deleteTaskById; id = {}", id);
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}