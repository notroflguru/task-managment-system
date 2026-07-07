package com.tms.mapper;

import com.tms.model.Task;
import com.tms.model.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {
    public TaskEntity toEntity (Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setTaskDescription(task.getTaskDescription());
        entity.setCreatorId(task.getCreatorId());
        entity.setDeadline(task.getDeadline());
        entity.setPriority(task.getPriority());
        entity.setStatus(task.getStatus());
        entity.setAssignedUserId(task.getAssignedUserId());
        entity.setCreateDateTime(task.getCreateDateTime());
        return entity;
    }

    public Task toDomain (TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getTaskDescription(),
                entity.getCreatorId(),
                entity.getAssignedUserId(),
                entity.getStatus(),
                entity.getCreateDateTime(),
                entity.getDeadline(),
                entity.getPriority()
        );
    }

    public List<Task> toDomainList(List<TaskEntity> entities) {
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }
}
