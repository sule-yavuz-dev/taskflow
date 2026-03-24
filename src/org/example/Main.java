package org.example;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task(1, "Learn Java", "Study OOP concepts", 2);
        Task task2 = new Task(2,"Go to gym", "Workout for 1 hour", 3);
        manager.addTask(task1);
        manager.addTask(task2);

        task1.markAsCompleted();
        manager.listTask();
    }
}
