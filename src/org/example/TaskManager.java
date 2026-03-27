package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks = new ArrayList<>();
    private int nextId =1;

    public void addTask(Task task){
        tasks.add(task);
        if(task.getId() >= nextId){
            nextId  = task.getId() + 1;
        }
    }

    public void addTask(String title, String description, int priority){
        Task task = new Task(nextId, title,description, priority);
        tasks.add(task);
        nextId++;
    }
    public void listTask(){
        for(Task task : tasks){
            System.out.println(
                    task.getId() + " - " + task.getTitle() + " - " + task.getStatus()
            );
        }
    }
    public void listCompletedTasks(){
        for(Task task : tasks){
            if(task.getStatus() == TaskStatus.COMPLETED){
                System.out.println(
                        task.getId() + " - " + task.getTitle() + " - "+ task.getStatus()
                );
            }
        }
    }

    public void listPendingTasks(){
        for(Task task : tasks){
            if(task.getStatus() == TaskStatus.PENDING){
                System.out.println(task.getId() + " - " + task.getTitle() + " - " + task.getStatus());
            }
        }
    }
    public Task findTaskById(int id){
        for(Task task : tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public void updateTaskTitle(int id, String newTitle){
        Task task = findTaskById(id);
        if(task != null){
            task.setTitle(newTitle);
            System.out.println("Task updated successfully");
        }else{
            System.out.println("Task not found");
        }
    }

    public void markTaskAsCompleted(int id){
        Task task = findTaskById(id);
        if(task != null){
            task.markAsCompleted();
            System.out.println("Task marked as completed");
        }else{
            System.out.println("Task not found");
        }
    }
    public void deleteTaskById(int id){
        boolean found = false;
        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getId() == id){
                tasks.remove(i);
                found = true;
                break;
            }
        }
        if(found){
            System.out.println("Task deleted successfully");
        }else{
            System.out.println("Task not found");
        }

    }

}
