package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        Task task1 = new Task(1, "Learn Java", "Study OOP concepts", 2);
        Task task2 = new Task(2, "Go to gym", "Workout for 1 hour", 3);
        manager.addTask(task1);
        manager.addTask(task2);
        task1.markAsCompleted();
        manager.listTask();

        while (true) {
            System.out.println("\n=== Task Manager ===");
            System.out.println("1.Add task");
            System.out.println("2.List tasks");
            System.out.println("3.Delete task");
            System.out.println("0.Exit");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.println("Enter task title: ");
                String title = scanner.nextLine();
                System.out.println("You entered: " + title);

                System.out.println("Enter description: ");
                String description = scanner.nextLine();
                System.out.println("You entered description: " + description);

                System.out.println("Enter priority: ");
                int priority = scanner.nextInt();
                scanner.nextLine();

                manager.addTask(title, description, priority);

                System.out.println("After adding new task: ");
                manager.listTask();
            }
            if(choice == 2){
                manager.listTask();
            }
            if(choice == 3){
                System.out.println("Enter task id to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                manager.deleteTaskById(id);
            }
            if(choice ==0){
                System.out.println("Exiting...");
                break;
            }

        }
    }
}
