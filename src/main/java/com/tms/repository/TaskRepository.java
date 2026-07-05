package com.tms.repository;

import com.tms.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskRepository {

    private final HashMap<Long, Task> taskHashMap;
    AtomicLong idCounter =  new AtomicLong();

    @Autowired
    public TaskRepository() {
        taskHashMap = new HashMap<>();
    }

//    HashMap<Long, Task> taskHashMap = new HashMap<>() {{
//        put(1L, new Task(
//                1L, "Debug TaskService", 1L, 3L,
//                Task.Status.ASSIGNED, LocalDateTime.now(), LocalDateTime.now().plusDays(5), Task.Priority.HIGH));
//
//        put(2L, new Task(2L, "Move Repository to JPA", 1L, 4L,
//                Task.Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now().plusDays(10), Task.Priority.MEDIUM));
//
//        put(3L, new Task(3L, "Change coffee machine", 2L, null,
//                Task.Status.CREATED, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(50), Task.Priority.LOW));
//    }};

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

    public Task createTask(Task task) {
        if (task.getId() != null) {
            throw new IllegalArgumentException("Id should be null");
        } else {
            Task newTask = new Task(idCounter.incrementAndGet(), task.getTaskDescription(), task.getCreatorId(), task.getAssignedUserId(), task.getStatus(), task.getCreateDateTime(), task.getDeadline(), task.getPriority());
            taskHashMap.put(newTask.getId(), newTask);
            if (taskHashMap.containsKey(newTask.getId())) {
                return newTask;
            } else {
                throw new RuntimeException("Произошла ошибка!");
            }
        }
    }
}
