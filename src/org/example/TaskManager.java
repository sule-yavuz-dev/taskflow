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
}
