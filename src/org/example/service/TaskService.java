package org.example.service;

import org.example.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task task);
    void addTask(String title, String description, int priority);
    void deleteTaskById(int id);
    void updateTask(int id, String newTitle, String newDescription);
    void updateTask(int id, Task updatedTask);
    void updateTaskPriority(int id, int newPriority);
    void markTaskAsCompleted(int id);
    void markTaskAsInProgress(int id);
    void listTask();
    void listCompletedTasks();
    void listPendingTasks();
    void listTasksByPriority();
    void searchTasks(String keyword);
    void listTasksSortedByPriority();
    void listHighPriorityTasks();


    List<Task> getAllTasks();

    Task getTaskById(int id);

}
