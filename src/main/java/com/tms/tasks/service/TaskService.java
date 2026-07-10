package com.tms.tasks.service;

import com.tms.tasks.dto.CreateTaskRequest;
import com.tms.tasks.dto.TaskResponse;
import com.tms.tasks.dto.UpdateTaskRequest;
import com.tms.tasks.mapper.TaskMapper;
import com.tms.tasks.model.Status;
import com.tms.tasks.model.TaskEntity;
import com.tms.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService (TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponse findTaskById(Long id) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id = " + id + " not found"));
        return taskMapper.toResponse(entity);
    }

    public List<TaskResponse> findAll() {
        return taskMapper.toResponseList(taskRepository.findAll());
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        TaskEntity entity = taskMapper.toEntity(request);
        entity.setStatus(Status.CREATED);
        entity.setCreateDateTime(LocalDateTime.now());
        taskRepository.save(entity);
        return taskMapper.toResponse(entity);
    }

    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task with id " + id + " does not exist"));
        if (request.getDeadline() != null) {
            entity.setDeadline(request.getDeadline());
        }
        if (request.getPriority() != null) {
            entity.setPriority(request.getPriority());
        }
        if (request.getStatus() != null) {
            entity.setStatus(request.getStatus());
        }
        if (request.getTaskDescription() != null) {
            entity.setTaskDescription(request.getTaskDescription());
        }
        if (request.getAssignedUserId() != null) {
            entity.setAssignedUserId(request.getAssignedUserId());
        }
        return taskMapper.toResponse(taskRepository.save(entity));
    }

    public void deleteTaskById(Long id) {
        if (taskRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Task with id " + id + " does not exist");
        }
        taskRepository.deleteById(id);
    }
}
