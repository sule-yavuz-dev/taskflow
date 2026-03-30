package org.example;

import java.util.ArrayList;
import java.util.Comparator;
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
                    task.getId() + " - " + task.getTitle() + " - " + task.getDescription() + " - " + getPriorityLabel(task)+" - "+ task.getStatus()
            );
        }
    }
    public void listCompletedTasks(){
        for(Task task : tasks){
            if(task.getStatus() == TaskStatus.COMPLETED){
                System.out.println(
                        task.getId() + " - " + task.getTitle() + " - "+ getPriorityLabel(task)+" - "+ task.getStatus()
                );
            }
        }
    }

    public void listPendingTasks(){
        for(Task task : tasks){
            if(task.getStatus() == TaskStatus.PENDING){
                System.out.println(task.getId() + " - " + task.getTitle() + " - " + task.getDescription() + " - "+ getPriorityLabel(task)+" - "+ task.getStatus());
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

    public void updateTask(int id, String newTitle,String newDescription){
        Task task = findTaskById(id);
        if(task != null){
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            System.out.println("Task updated successfully");
        }else{
            System.out.println("Task not found");
        }
    }
    public void updateTaskPriority(int id, int newPriority){
        Task task = findTaskById(id);
        if(task != null){
            task.setPriority(newPriority);
            System.out.println("Task priority updated successfully");
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

    public void markTaskAsInProgress(int id){
        Task task = findTaskById(id);
        if(task != null){
            task.markAsInProgress();
            System.out.println("Task marked as IN_PROGRESS");
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
    private String getPriorityLabel(Task task){
        if(task.getStatus() == TaskStatus.COMPLETED){
            return "No active priority";
        }
        int priority = task.getPriority();
        if(priority == 1 || priority == 2){
            return "High priority";
        }else if(priority ==3){
            return "Medium priority";
        }else{
            return "Low priority";
        }
    }
    public void listTasksByPriority(){
        System.out.println("=== HIGH PRIORITY ===");
        boolean foundHigh = false;
        for(Task task : tasks){
            if(task.getStatus() != TaskStatus.COMPLETED && (task.getPriority() == 1 || task.getPriority() == 2)){
                System.out.println(task.getId() + " - " + task.getTitle());
                foundHigh = true;
            }
        }
        if(!foundHigh){
            System.out.println("No tasks found");
        }
        System.out.println("\n=== MEDIUM PRIORITY ===");
        boolean foundMedium = false;
        for(Task task : tasks){
            if(task.getStatus() != TaskStatus.COMPLETED && task.getPriority() ==3){
                System.out.println(task.getId() + " - " + task.getTitle());
                foundMedium = true;
            }
        }
        if(!foundMedium){
            System.out.println("No tasks found");
        }
        System.out.println("\n === LOW PRIORITY ===");
        boolean foundLow = false;
        for(Task task : tasks){
            if(task.getStatus() != TaskStatus.COMPLETED && task.getPriority() > 3){
                System.out.println(task.getId() + " - " + task.getTitle());
                foundLow = true;
            }
        }
        if(!foundLow){
            System.out.println("No tasks found");
        }
        System.out.println("\n === COMPLETED ===");
        boolean foundCompleted = false;
        for(Task task : tasks){
            if(task.getStatus() == TaskStatus.COMPLETED){
                System.out.println(task.getId()+ " - " + task.getTitle());
                foundCompleted = true;
            }
        }
        if(!foundCompleted){
            System.out.println("No tasks found");
        }
    }
    public void searchTasks(String keyword){
        boolean found = false;
        System.out.println("=== SEARCH RESULTS ===");
        String searchKey = keyword.toLowerCase();
        for(Task task : tasks){
            if(task. getTitle().toLowerCase().contains(searchKey) || task.getDescription().toLowerCase().contains(searchKey)){
                System.out.println(task.getId()+ " - "+ task.getTitle()+ " - " + task.getDescription()+ " - "+getPriorityLabel(task) + " - "+task.getStatus()
                );
                found = true;
            }
        }
        if(!found){
            System.out.println("No tasks found");
        }
    }
    public void listTasksSortedByPriority(){
        if(tasks.isEmpty()){
            System.out.println("No tasks available");
            return;
        }
        List<Task> sortedTasks = new ArrayList<>(tasks);

        sortedTasks.sort(Comparator.comparing((Task t) -> t.getStatus() == TaskStatus.COMPLETED).thenComparingInt(Task::getPriority));

        for(Task task : sortedTasks){
            System.out.println(
                    task.getId() + " - " + task.getTitle() + " - "+task.getDescription() + " - "+ getPriorityLabel(task)+" - "+ task.getStatus()
            );
        }

    }

}
