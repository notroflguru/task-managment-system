package com.tms.tasks.dto;

import com.tms.tasks.model.Status;

public class ChangeTaskStatusRequest {
    private Status status;
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
