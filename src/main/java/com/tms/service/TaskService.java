package com.tms.service;

import com.tms.dto.CreateTaskRequest;
import com.tms.mapper.TaskMapper;
import com.tms.model.Status;
import com.tms.model.Task;
import com.tms.model.TaskEntity;
import com.tms.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService (TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Task findTaskById(Long id) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id = " + id + " not found"));
        return taskMapper.toDomain(entity);
    }

    public List<Task> findAll() {
        return taskMapper.toDomainList(taskRepository.findAll());
    }

    public Task createTask(CreateTaskRequest request) {
        Task newTask = new Task(null, request.getTaskDescription(), request.getCreatorId(),
                request.getAssignedUserId(), Status.CREATED, LocalDateTime.now(), request.getDeadline(), request.getPriority());
        TaskEntity entity = taskRepository.save(taskMapper.toEntity(newTask));
        Task resTask = taskMapper.toDomain(entity);
        return resTask;
    }

    public Task changeTaskStatus(Long id, Status status) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " does not exist"));
        entity.setStatus(status);
        taskRepository.save(entity);
        return taskMapper.toDomain(entity);
    }

    public void deleteTaskById(Long id) {
        if (taskRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Task with id " + id + " does not exist");
        }
        taskRepository.deleteById(id);
    }
}
