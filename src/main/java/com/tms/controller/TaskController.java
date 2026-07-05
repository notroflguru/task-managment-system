package com.tms.controller;

import com.tms.model.Task;
import com.tms.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("Вызван поиск задачи по id = {}", id);
        return taskService.findTaskById(id);
    }

}
