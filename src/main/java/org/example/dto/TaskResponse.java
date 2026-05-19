package org.example.dto;

import org.example.TaskStatus;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private int priority;
    private TaskStatus status;

    public TaskResponse(Long id, String title, String description, int priority, TaskStatus status){
        this.id= id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;

    }
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
