package com.tms.dto;

import com.tms.model.Priority;

import java.time.LocalDateTime;

public class CreateTaskRequest {

    private String taskDescription;
    private Long creatorId;
    private Long assignedUserId;
    private LocalDateTime deadline;
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
