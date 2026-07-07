package com.tms.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class Task {

    private Long id;
    private String taskDescription;
    private Long creatorId;
    private Long assignedUserId;
    private Status status;
    private LocalDateTime createDateTime;
    private LocalDateTime deadline;
    private Priority priority;

    public Task(Long id, String taskDescription, Long creatorId, Long assignedUserId, Status status, LocalDateTime createDateTime, LocalDateTime deadline, Priority priority) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.creatorId = creatorId;
        this.assignedUserId = assignedUserId;
        this.status = status;
        this.createDateTime = createDateTime;
        this.deadline = deadline;
        this.priority = priority;
    }

    // Геттеры
    public Long getId() {
        return id;
    }
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
    public void setId(Long newId) {
        this.id = newId;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
