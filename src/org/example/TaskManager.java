package org.example;

import org.example.exception.TaskNotFoundException;
import org.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TaskManager implements TaskService {

    private List<Task> tasks = new ArrayList<>();
    private int nextId =1;


    @Override
    public void addTask(Task task){
        tasks.add(task);
        if(task.getId() >= nextId){
            nextId  = task.getId() + 1;
        }
    }

    @Override
    public void addTask(String title, String description, int priority){
        Task task = new Task(nextId, title,description, priority);
        tasks.add(task);
        nextId++;
    }
    @Override
    public void listTask(){
        if (isEmptyAndPrintMessage()) return;
        for(Task task : tasks){
            System.out.println(
                    task.getId() + " - " + task.getTitle() + " - " + task.getDescription() + " - " + getPriorityLabel(task)+" - "+ task.getStatus()
            );
        }
    }
    @Override
    public void listCompletedTasks(){
        if (isEmptyAndPrintMessage()) return;
        boolean hasCompleted = tasks.stream().anyMatch(task -> task.getStatus() == TaskStatus.COMPLETED);

        if (!hasCompleted) {
            System.out.println("No completed tasks found");
            return;
        }
        for(Task task : tasks){
            if(task.getStatus() == TaskStatus.COMPLETED){
                System.out.println(
                        task.getId() + " - " + task.getTitle() + " - "+ getPriorityLabel(task)+" - "+ task.getStatus()
                );
            }
        }
    }

    @Override
    public void listPendingTasks(){
        if (isEmptyAndPrintMessage()) return;
        boolean hasPending = tasks.stream().anyMatch(task -> task.getStatus() == TaskStatus.PENDING);

        if(!hasPending){
            System.out.println("No pending tasks found");
            return;
        }
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
        throw new TaskNotFoundException("Task not found with id: "+ id);
    }

    @Override
    public void updateTask(int id, String newTitle,String newDescription){
        Task task = findTaskById(id);

            task.setTitle(newTitle);
            task.setDescription(newDescription);
            System.out.println("Task updated successfully");
        }

    @Override
    public void updateTask(int id, Task updatedTask) {
        Task task = findTaskById(id);

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
    }

    @Override
    public void updateTaskPriority(int id, int newPriority) {
        Task task = findTaskById(id);

        task.setPriority(newPriority);
        System.out.println("Task priority updated successfully");
    }

    @Override
    public void markTaskAsCompleted(int id){
        Task task = findTaskById(id);

            task.markAsCompleted();
            System.out.println("Task marked as completed");

    }

    @Override
    public void markTaskAsInProgress(int id){
        Task task = findTaskById(id);

            task.markAsInProgress();
            System.out.println("Task marked as IN_PROGRESS");
    }
    @Override
    public void deleteTaskById(int id){
            Task task = findTaskById(id);
            tasks.remove(task);
            System.out.println("Task deleted successfully");
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
    @Override
    public void listTasksByPriority(){

        if(isEmptyAndPrintMessage()) return;
        System.out.println("=== HIGH PRIORITY ===");
        boolean foundHigh = false;
        for(Task task : tasks){
            if(task.getStatus() != TaskStatus.COMPLETED && (task.getPriority() == 1 || task.getPriority() == 2)){
                System.out.println(task.getId() + " - " + task.getTitle());
                foundHigh = true;
            }
        }
        if(!foundHigh){
            System.out.println("No high priority tasks found");
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
            System.out.println("No medium priority tasks found");
        }
        System.out.println("\n=== LOW PRIORITY ===");
        boolean foundLow = false;
        for(Task task : tasks){
            if(task.getStatus() != TaskStatus.COMPLETED && task.getPriority() > 3){
                System.out.println(task.getId() + " - " + task.getTitle());
                foundLow = true;
            }
        }
        if(!foundLow){
            System.out.println("No low priority tasks found");
        }
        System.out.println("\n=== COMPLETED ===");
        boolean foundCompleted = false;
        for(Task task : tasks){
            if(task.getStatus() == TaskStatus.COMPLETED){
                System.out.println(task.getId()+ " - " + task.getTitle());
                foundCompleted = true;
            }
        }
        if(!foundCompleted){
            System.out.println("No completed tasks found");
        }
    }
    @Override
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
    @Override
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
    private boolean isEmptyAndPrintMessage(){
        if(tasks.isEmpty()){
            System.out.println("No tasks available");
            return true;
        }
        return false;
    }
    @Override
    public void listHighPriorityTasks(){
        if(isEmptyAndPrintMessage())return;

        boolean hasHighPriority = tasks.stream().anyMatch(task -> task.getStatus() != TaskStatus.COMPLETED &&(task.getPriority() == 1 || task.getPriority() ==2));

        if(!hasHighPriority){
            System.out.println("No high priority tasks found");
            return;
        }
        for(Task task : tasks){
            if(task.getStatus() != TaskStatus.COMPLETED &&(task.getPriority() == 1 || task.getPriority() == 2)){
                System.out.println(task.getId()+" - "+ task.getTitle()+ " - " + task.getDescription()+" - "+getPriorityLabel(task)+ " - " +task.getStatus());
            }
        }
    }
    @Override
    public List<Task> getAllTasks(){
        return tasks;
    }

    public Task getTaskById(int id){
        return tasks.stream().filter(task ->task.getId() == id).findFirst().orElseThrow(()-> new TaskNotFoundException("Task not found with id: " + id));
    }
}
