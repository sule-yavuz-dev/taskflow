package org.example;

import org.example.exception.TaskNotFoundException;
import org.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TaskManager implements TaskService {

    private final TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(Task task){
        taskRepository.save(task);
    }

    @Override
    public Task addTask(String title, String description, int priority){
        Task task = new Task(title, description, priority);
        return taskRepository.save(task);
    }
    @Override
    public void listTask(){
        if (isEmptyAndPrintMessage()) return;
        List<Task> allTasks = taskRepository.findAll();
        for(Task task : allTasks){
            System.out.println(
                    task.getId() + " - " + task.getTitle() + " - " + task.getDescription() + " - " + getPriorityLabel(task)+" - "+ task.getStatus()
            );
        }
    }
    @Override
    public void listCompletedTasks(){
        if (isEmptyAndPrintMessage()) return;

        List<Task> allTasks = taskRepository.findAll();

        boolean hasCompleted = allTasks.stream().anyMatch(task -> task.getStatus() == TaskStatus.COMPLETED);

        if (!hasCompleted) {
            System.out.println("No completed tasks found");
            return;
        }

        for(Task task : allTasks){
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

        List<Task> allTasks = taskRepository.findAll();

        boolean hasPending = allTasks.stream().anyMatch(task -> task.getStatus() == TaskStatus.PENDING);

        if(!hasPending){
            System.out.println("No pending tasks found");
            return;
        }
        for(Task task : allTasks){
            if(task.getStatus() == TaskStatus.PENDING){
                System.out.println(task.getId() + " - " + task.getTitle() + " - " + task.getDescription() + " - "+ getPriorityLabel(task)+" - "+ task.getStatus());
            }
        }
    }

    @Override
    public void updateTask(Long id, String newTitle,String newDescription){
        Task task = getTaskById(id);

            task.setTitle(newTitle);
            task.setDescription(newDescription);
            taskRepository.save(task);
            System.out.println("Task updated successfully");
        }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());

        return taskRepository.save(task);
    }

    @Override
    public void updateTaskPriority(Long id, int newPriority) {
        Task task = getTaskById(id);

        task.setPriority(newPriority);
        taskRepository.save(task);
        System.out.println("Task priority updated successfully");
    }

    @Override
    public void markTaskAsCompleted(Long id){
        Task task = getTaskById(id);

            task.markAsCompleted();
            taskRepository.save(task);
            System.out.println("Task marked as completed");

    }

    @Override
    public void markTaskAsInProgress(Long id){
        Task task = getTaskById(id);

            task.markAsInProgress();
            taskRepository.save(task);
            System.out.println("Task marked as IN_PROGRESS");
    }
    @Override
    public void deleteTaskById(Long id){
            Task task = getTaskById(id);
            taskRepository.delete(task);
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

        List<Task> allTasks = taskRepository.findAll();
        System.out.println("=== HIGH PRIORITY ===");
        boolean foundHigh = false;
        for(Task task : allTasks){
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
        for(Task task : allTasks){
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
        for(Task task : allTasks){
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
        for(Task task : allTasks){
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
        List<Task> allTasks = taskRepository.findAll();
        for(Task task : allTasks){
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
        if(taskRepository.count() == 0){
            System.out.println("No tasks available");
            return;
        }
        List<Task> sortedTasks = new ArrayList<>(taskRepository.findAll());

        sortedTasks.sort(Comparator.comparing((Task t) -> t.getStatus() == TaskStatus.COMPLETED).thenComparingInt(Task::getPriority));

        for(Task task : sortedTasks){
            System.out.println(
                    task.getId() + " - " + task.getTitle() + " - "+task.getDescription() + " - "+ getPriorityLabel(task)+" - "+ task.getStatus()
            );
        }

    }
    private boolean isEmptyAndPrintMessage(){
        if(taskRepository.count() == 0){
            System.out.println("No tasks available");
            return true;
        }
        return false;
    }
    @Override
    public void listHighPriorityTasks(){
        if(isEmptyAndPrintMessage())return;

        List<Task> allTasks = taskRepository.findAll();
        boolean hasHighPriority = allTasks.stream().anyMatch(task -> task.getStatus() != TaskStatus.COMPLETED &&(task.getPriority() == 1 || task.getPriority() ==2));

        if(!hasHighPriority){
            System.out.println("No high priority tasks found");
            return;
        }
        for(Task task : allTasks){
            if(task.getStatus() != TaskStatus.COMPLETED &&(task.getPriority() == 1 || task.getPriority() == 2)){
                System.out.println(task.getId()+" - "+ task.getTitle()+ " - " + task.getDescription()+" - "+getPriorityLabel(task)+ " - " +task.getStatus());
            }
        }
    }
    @Override
    public List<Task> getAllTasks(){

        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status){
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByPriority(int priority){
        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> searchTaskForApi(String keyword){
        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword,keyword);
    }

    @Override
    public Page<Task> getTasksWithPagination(Pageable pageable){
        return taskRepository.findAll(pageable);
    }
}
