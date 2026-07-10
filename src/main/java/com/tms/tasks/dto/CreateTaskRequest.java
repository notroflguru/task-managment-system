package com.tms.tasks.dto;

import com.tms.tasks.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateTaskRequest {

    @NotBlank
    private String taskDescription;

    @NotNull
    private Long creatorId;

    @NotNull
    private Long assignedUserId;

    @NotNull
    private LocalDateTime deadline;

    @NotNull
    private Priority priority;

    // Геттеры
    public String getTaskDescription() {
        return taskDescription;
    }
    public Long getCreatorId() {
        return creatorId;
    }
    public Long getAssignedUserId() {
        return assignedUserId;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public Priority getPriority() {
        return priority;
    }

    // Сеттеры

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
