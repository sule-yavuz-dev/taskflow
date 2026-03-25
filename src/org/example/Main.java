package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        Task task1 = new Task(1, "Learn Java", "Study OOP concepts", 2);
        Task task2 = new Task(2,"Go to gym", "Workout for 1 hour", 3);
        manager.addTask(task1);
        manager.addTask(task2);

        task1.markAsCompleted();
        manager.listTask();

        manager.deleteTaskById(1);

        System.out.println("After delete: ");
        manager.listTask();

        System.out.println("Enter task title: ");
        String title = scanner.nextLine();
        System.out.println("You entered: " + title);

        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("You entered description: " + description);

        System.out.println("Enter priority: ");
        int priority = scanner.nextInt();

        manager.addTask(title,description,priority);

        System.out.println("After adding new task: ");
        manager.listTask();
    }

}
