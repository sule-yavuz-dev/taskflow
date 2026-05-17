package org.example.controller;
import jakarta.validation.Valid;
import org.example.TaskStatus;
import org.example.dto.TaskRequest;
import org.example.dto.TaskResponse;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.example.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<TaskResponse> getTasks( @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getTasksWithPagination(pageable).map(this::toTaskResponse);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest request){
        Task savedTask = taskService.addTask(request.getTitle(),request.getDescription(), request.getPriority());
        return ResponseEntity.status(HttpStatus.CREATED).body(toTaskResponse(savedTask));
    }


    @GetMapping("/tasks/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        Task task = taskService.getTaskById(id);
        return toTaskResponse(task);
    }

    @GetMapping("/tasks/status/{status}")
    public List<TaskResponse> getTasksByStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status).stream().map(this::toTaskResponse).toList();
    }

    @GetMapping("/tasks/priority/{priority}")
    public List<TaskResponse> getTaskByPriority(@PathVariable int priority){
        return taskService.getTasksByPriority(priority).stream().map(this::toTaskResponse).toList();
    }

    @GetMapping("/tasks/search")
    public List<TaskResponse> searchTasks(@RequestParam String keyword){
        return taskService.searchTaskForApi(keyword).stream().map(this::toTaskResponse).toList();
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


    private TaskResponse toTaskResponse(Task task){
        return new TaskResponse(
                task.getId(),task.getTitle(),task.getDescription(),task.getPriority(),task.getStatus()
        );
    }
}
