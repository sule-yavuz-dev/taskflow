package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task){
        tasks.add(task);
    }

    public void listTask(){
        for(Task task : tasks){
            System.out.println(
                    task.getId() + " - " + task.getTitle() + " - " + task.isCompleted()
            );
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
