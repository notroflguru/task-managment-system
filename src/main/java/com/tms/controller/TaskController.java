package com.tms.controller;

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
    public List<Task> findAll() {
        log.info("Вызван метод findAll()");
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task findTaskById(
            @PathVariable("id") Long id
    ) {
        log.info("Вызван метод findTaskById по id = {}", id);
        return taskService.findTaskById(id);
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(
            @RequestBody Task task
    ) {
        log.info("Вызван метод createTask");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }
}
