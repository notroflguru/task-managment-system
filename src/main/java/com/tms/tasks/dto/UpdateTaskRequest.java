package com.tms.tasks.dto;

import com.tms.tasks.model.Priority;
import com.tms.tasks.model.Status;

import java.time.LocalDateTime;

public class UpdateTaskRequest {
    private Status status;
    private String taskDescription;
    private Long assignedUserId;
    private LocalDateTime deadline;
    private Priority priority;

    // Геттеры
    public Status getStatus() {
        return status;
    }
    public String getTaskDescription() {
        return taskDescription;
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

    public void setStatus(Status status) {
        this.status = status;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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
