package com.tms.repository;

import com.tms.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class TaskRepository {

    @Autowired
    public TaskRepository() {}

    HashMap<Long, Task> taskHashMap = new HashMap<>() {{
        put(1L, new Task(
                1L, "Debug TaskService", 1L, 3L,
                Task.Status.ASSIGNED, LocalDateTime.now(), LocalDateTime.now().plusDays(5), Task.Priority.HIGH));

        put(2L, new Task(2L, "Move Repository to JPA", 1L, 4L,
                Task.Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now().plusDays(10), Task.Priority.MEDIUM));

        put(3L, new Task(3L, "Change coffee machine", 2L, null,
                Task.Status.CREATED, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(50), Task.Priority.LOW));
    }};

    public Task findTaskById(Long id) {
        if (!taskHashMap.containsKey(id)) {
            throw new NoSuchElementException("Задачи с таким id не существует");
        }
        return taskHashMap.get(id);
    }

    public List<Task> findAll() {
        if (taskHashMap.isEmpty()) {
            throw new IllegalArgumentException("Список задач пуст!");
        }
        return taskHashMap.values().stream().toList();
    }
}
