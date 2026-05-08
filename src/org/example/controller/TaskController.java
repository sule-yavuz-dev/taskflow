package org.example.controller;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.example.Task;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/test")
    public String test(){
        return "API is working!";
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable int id){
        return taskService.getTaskById(id);
    }
}
