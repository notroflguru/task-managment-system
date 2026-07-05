package com.tms.repository;

import com.tms.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskRepository {

    private final HashMap<Long, Task> taskHashMap;
    AtomicLong idCounter =  new AtomicLong();

    public TaskRepository() {
        taskHashMap = new HashMap<>();
    }


    public Optional<Task> findTaskById(Long id) {
        return Optional.ofNullable(taskHashMap.get(id));
    }

    public List<Task> findAll() {
        return taskHashMap.values().stream().toList();
    }

    public Task createTask(Task task) {
        Task newTask = new Task(idCounter.incrementAndGet(), task.getTaskDescription(), task.getCreatorId(), task.getAssignedUserId(), Task.Status.CREATED, task.getCreateDateTime(), task.getDeadline(), task.getPriority());
        taskHashMap.put(newTask.getId(), newTask);
        return newTask;
    }

    public Task changeTaskStatus(Long id, Task.Status status) {
        Task newTask = taskHashMap.get(id);
        newTask.setStatus(status);
        taskHashMap.remove(id);
        taskHashMap.put(id, newTask);
        return newTask;
    }

    public void deleteTaskById(Long id) {
        taskHashMap.remove(id);
    }
}
