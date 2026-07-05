package com.tms.dto;

import com.tms.model.Task;

public class ChangeTaskStatusRequest {
    private Task.Status status;
    public Task.Status getStatus() {
        return this.status;
    }
    public void setStatus(Task.Status status) {
        this.status = status;
    }
}
