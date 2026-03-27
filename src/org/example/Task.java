package org.example;

public class Task {
    private int id;
    private String title;
    private String description;
    private int priority;
    private boolean completed;


    public Task(int id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
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

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted(){
        this.completed = true;
    }

    public void setTitle(String title){
        this.title = title;
    }
}