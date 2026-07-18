package com.tms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String code;
    private String message;
    private Map<String, String> errors;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public ErrorResponse(String code, String message, LocalDateTime time) {
        this.code = code;
        this.message = message;
        this.time = time;
    }

    public ErrorResponse(String code, String message, Map<String, String> errors, LocalDateTime time) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.time = time;
    }

    // Геттеры
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public Map<String, String> getErrors() {return errors;}
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
    public void setErrors(Map<String, String> errors) {this.errors = errors;}
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
