package com.tms.tasks.dto;

import com.tms.tasks.model.Priority;
import com.tms.tasks.model.Status;

import java.time.LocalDateTime;

public class TaskResponse {

    private Long id;

    private String taskDescription;

    private Long creatorId;

    private Long assignedUserId;

    private Status status;

    private LocalDateTime createDateTime;

    private LocalDateTime deadline;

    private Priority priority;

    // Геттеры
    public Long getId() {return id;}
    public String getTaskDescription() {
        return taskDescription;
    }
    public Long getCreatorId() {
        return creatorId;
    }
    public Long getAssignedUserId() {
        return assignedUserId;
    }
    public Status getStatus() {
        return status;
    }
    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public Priority getPriority() {
        return priority;
    }

    // Сеттеры
    public void setId(Long id) {this.id = id;}
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
