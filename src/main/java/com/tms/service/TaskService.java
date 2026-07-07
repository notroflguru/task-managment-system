package com.tms.service;

import com.tms.dto.CreateTaskRequest;
import com.tms.model.Task;
import com.tms.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findTaskById(Long id) {
        return taskRepository.findTaskById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " was not found"));
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task createTask(CreateTaskRequest request) {
        Task newTask = new Task(null, request.getTaskDescription(), request.getCreatorId(),
                request.getAssignedUserId(), null, LocalDateTime.now(), request.getDeadline(), request.getPriority());
        return taskRepository.createTask(newTask);
    }

    public Task changeTaskStatus(Long id, Task.Status status) {
        if (taskRepository.findTaskById(id).isEmpty()) {
            throw new NoSuchElementException("Task with id " + id + " does not exist");
        }
        return taskRepository.changeTaskStatus(id, status);
    }

    public void deleteTaskById(Long id) {
        if (taskRepository.findTaskById(id).isEmpty()) {
            throw new NoSuchElementException("Task with id " + id + " does not exist");
        }
        taskRepository.deleteTaskById(id);
    }
}
