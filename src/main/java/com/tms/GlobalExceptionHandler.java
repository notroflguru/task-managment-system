package com.tms;

import com.tms.dto.ErrorResponse;
import com.tms.tasks.exception.TaskNotFoundException;
import com.tms.users.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException (NoSuchElementException nse) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nse.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException (TaskNotFoundException e) {
        log.error("Task Not Found exception: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("TASK_NOT_FOUND", e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException (UserNotFoundException e) {
        log.error("User Not Found exception: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("USER_NOT_FOUND", e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValidException (MethodArgumentNotValidException e) {
        log.warn("Not valid user request: {}", e.getMessage());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ErrorResponse errorResponse = new ErrorResponse("NOT_VALID_REQUEST", "Validation failed", errors, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException (DataIntegrityViolationException e) {
        log.error("Data integrity violation: {}", e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("DATA_INTEGRITY_VIOLATION", "Database constraint violation", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
