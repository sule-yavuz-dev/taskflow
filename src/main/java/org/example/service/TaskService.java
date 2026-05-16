package org.example.service;

import org.example.Task;
import org.example.TaskStatus;

import java.util.List;

public interface TaskService {
    void addTask(Task task);
    Task addTask(String title, String description, int priority);
    void deleteTaskById(Long id);
    void updateTask(Long id, String newTitle, String newDescription);
    void updateTask(Long id, Task updatedTask);
    void updateTaskPriority(Long id, int newPriority);
    void markTaskAsCompleted(Long id);
    void markTaskAsInProgress(Long id);
    void listTask();
    void listCompletedTasks();
    void listPendingTasks();
    void listTasksByPriority();
    void searchTasks(String keyword);
    List<Task> searchTaskForApi(String keyword);
    void listTasksSortedByPriority();
    void listHighPriorityTasks();


    List<Task> getAllTasks();

    Task getTaskById(Long id);

    List<Task> getTasksByStatus(TaskStatus status);
    List<Task> getTasksByPriority(int priority);


}
