package com.tms.dto;

import com.tms.model.Status;

public class ChangeTaskStatusRequest {
    private Status status;
    public Status getStatus() {
        return this.status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
