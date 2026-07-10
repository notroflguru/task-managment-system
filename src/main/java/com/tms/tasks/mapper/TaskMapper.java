package com.tms.tasks.mapper;

import com.tms.tasks.dto.CreateTaskRequest;
import com.tms.tasks.dto.TaskResponse;
import com.tms.tasks.model.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskResponse toResponse(TaskEntity entity) {
        TaskResponse response = new TaskResponse();
        response.setId(entity.getId());
        response.setAssignedUserId(entity.getAssignedUserId());
        response.setCreateDateTime(entity.getCreateDateTime());
        response.setDeadline(entity.getDeadline());
        response.setPriority(entity.getPriority());
        response.setStatus(entity.getStatus());
        response.setTaskDescription(entity.getTaskDescription());
        response.setCreatorId(entity.getCreatorId());
        return response;
    }

    public List<TaskResponse> toResponseList(List<TaskEntity> taskEntityList) {
        return taskEntityList.stream().map(this::toResponse).toList();
    }

    public TaskEntity toEntity(CreateTaskRequest request) {
        TaskEntity entity = new TaskEntity();
        entity.setTaskDescription(request.getTaskDescription());
        entity.setAssignedUserId(request.getAssignedUserId());
        entity.setPriority(request.getPriority());
        entity.setDeadline(request.getDeadline());
        entity.setCreatorId(request.getCreatorId());
        return entity;
    }
}
