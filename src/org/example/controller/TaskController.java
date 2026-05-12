package org.example.controller;
import jakarta.validation.Valid;
import org.example.TaskStatus;
import org.example.dto.TaskRequest;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Task> addTask(@Valid @RequestBody TaskRequest request){
        Task savedTask = taskService.addTask(request.getTitle(),request.getDescription(), request.getPriority());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }


    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @GetMapping("/tasks/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    @GetMapping("/tasks/priority/{priority}")
    public List<Task> getTaskByPriority(@PathVariable int priority){
        return taskService.getTasksByPriority(priority);
    }


    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequest request){
        taskService.updateTask(id, request.getTitle(),request.getDescription());
        taskService.updateTaskPriority(id,request.getPriority());
    }
}
