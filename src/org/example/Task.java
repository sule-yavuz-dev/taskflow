package org.example;

public class Task {
    private int id;
    private String title;
    private String description;
    private int priority;
    private TaskStatus status;


    public Task(int id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
    }

    public int getId() {
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
}