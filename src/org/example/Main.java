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
        MenuHandler menuHandler = new MenuHandler();

        while (true) {
            menuHandler.showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAddTask(scanner, manager);
                    break;
                case 2:
                    manager.listTask();
                    break;
                case 3:
                    handleDeleteTask(scanner,manager);
                    break;
                case 4:
                    handleUpdateTask(scanner, manager);
                    break;
                case 5:
                    handleMarkAsCompleted(scanner,manager);
                    break;
                case 6:
                    manager.listCompletedTasks();
                    break;
                case 7:
                    manager.listPendingTasks();
                    break;
                case 8:
                    handleMarkAsInProgress(scanner,manager);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void handleAddTask(Scanner scanner, TaskManager manager) {
        String title;
        while(true){
        System.out.println("Enter task title: ");
        title = scanner.nextLine();

        if (!title.isEmpty()){
            break;
        }
            System.out.println("Title cannot be empty. Try again.");
        }
        String description;
        while(true){
        System.out.println("Enter description: ");
        description = scanner.nextLine();

        if(!description.isEmpty()){
            break;
        }
            System.out.println("Description cannot be empty. Try again.");
        }
        System.out.println("You entered description: " + description);

        int priority;
        while(true) {
            System.out.println("Enter priority: ");
            if (scanner.hasNextInt()) {
                priority = scanner.nextInt();
                scanner.nextLine();

                if(priority >= 1 && priority <=5){
                    break;
                }else{
                    System.out.println("Priority must be between 1 and 5!");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        manager.addTask(title, description, priority);

        System.out.println("After adding new task: ");
        manager.listTask();
    }
    private static void handleDeleteTask(Scanner scanner, TaskManager manager){
        System.out.println("Enter task id to delete: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine();
        manager.deleteTaskById(deleteId);
    }
    private static void handleUpdateTask(Scanner scanner, TaskManager manager){
        System.out.println("Enter task id to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new title: ");
        String newTitle = scanner.nextLine();

        System.out.println("Enter new description: ");
        String newDescription = scanner.nextLine();

        manager.updateTask(updateId, newTitle, newDescription);
    }

    private static void handleMarkAsCompleted(Scanner scanner, TaskManager manager){
        System.out.println("Enter task id to mark as completed: ");
        int completedId = scanner.nextInt();
        scanner.nextLine();
        manager.markTaskAsCompleted(completedId);
    }
    private static void handleMarkAsInProgress(Scanner scanner, TaskManager manager){
        System.out.println("Enter task id to mark as IN_PROGRESS: ");
        int progressId = scanner.nextInt();
        scanner.nextLine();
        manager.markTaskAsInProgress(progressId);
    }

}


