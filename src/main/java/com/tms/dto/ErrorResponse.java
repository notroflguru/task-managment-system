package com.tms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ErrorResponse {

    private String code;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public ErrorResponse(String code, String message, LocalDateTime time) {
        this.code = code;
        this.message = message;
        this.time = time;
    }

    // Геттеры
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public LocalDateTime getTime() {
        return time;
    }

    // Сеттеры
    public void setCode(String code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
