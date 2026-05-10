package org.example.controller;
import jakarta.validation.Valid;
import org.example.dto.TaskRequest;
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
    public void addTask(@Valid @RequestBody TaskRequest request){
        taskService.addTask(request.getTitle(),request.getDescription(), request.getPriority());
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequest request){
        taskService.updateTask(id, request.getTitle(),request.getDescription());
        taskService.updateTaskPriority(id,request.getPriority());
    }
}
