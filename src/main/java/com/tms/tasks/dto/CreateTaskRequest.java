package com.tms.tasks.dto;

import com.tms.tasks.model.Priority;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class CreateTaskRequest {

    @NotBlank(message = "Task description should not be blank")
    @Size(max=255, message = "Task description must not exceed 255 characters")
    private String taskDescription;

    @NotNull(message = "Creator id is required")
    private Long creatorId;

    @NotNull(message = "Assigned user id is required")
    private Long assignedUserId;

    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline must be in future")
    private LocalDateTime deadline;

    @NotNull(message = "Priority is required")
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
