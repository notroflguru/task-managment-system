package com.tms.service;

import com.tms.model.Task;
import com.tms.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findTaskById(Long id) {
        try {
            return taskRepository.findTaskById(id);
        } catch (NoSuchElementException nse) {
            log.error("Caught NoSuchElementException: {}", nse.getMessage());
            return null;
        }
    }

    public List<Task> findAll() {
        try {
            return taskRepository.findAll();
        } catch (IllegalArgumentException iae) {
            log.error("Caught IllegalArgumentException: {}", iae.getMessage());
            return null;
        }
    }

    public Task createTask(Task task) {
        try {
            return taskRepository.createTask(task);
        } catch (Exception e) {
            log.error("Caught exception: {}", e.getMessage());
            return null;
        }
    }
}
