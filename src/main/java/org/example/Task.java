package org.example;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int priority;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task(){

    }
    public Task(String title, String description,int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
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

    public void markAsCompleted(){
        this.status = TaskStatus.COMPLETED;
    }
    public void markAsInProgress(){
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }
}