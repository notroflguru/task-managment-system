package com.tms.tasks.service;

import com.tms.tasks.dto.CreateTaskRequest;
import com.tms.tasks.dto.TaskResponse;
import com.tms.tasks.dto.UpdateTaskRequest;
import com.tms.tasks.exception.TaskNotFoundException;
import com.tms.tasks.mapper.TaskMapper;
import com.tms.tasks.model.Status;
import com.tms.tasks.model.TaskEntity;
import com.tms.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private TaskEntity findTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    public TaskService (TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponse findTaskById(Long id) {
        TaskEntity entity = findTask(id);
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

        TaskEntity entity = findTask(id);

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
        TaskEntity entity = findTask(id);
        taskRepository.delete(entity);
    }
}
