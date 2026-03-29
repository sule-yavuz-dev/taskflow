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
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    handleAddTask(scanner, manager);
                    break;
                case 2:
                    manager.listTask();
                    break;
                case 3:
                    handleDeleteTask(scanner, manager);
                    break;
                case 4:
                    handleUpdateTask(scanner, manager);
                    break;
                case 5:
                    handleMarkAsCompleted(scanner, manager);
                    break;
                case 6:
                    manager.listCompletedTasks();
                    break;
                case 7:
                    manager.listPendingTasks();
                    break;
                case 8:
                    handleMarkAsInProgress(scanner, manager);
                    break;
                case 9:
                    manager.listTasksByPriority();
                    break;
                case 10:
                    handleUpdatePriority(scanner,manager);
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
        String title = readNonEmptyString(
                scanner,
                "Enter task title:"
        );

        String description= readNonEmptyString(
                scanner,
                "Enter description:"
        );
        int priority = readPriority(scanner);
        manager.addTask(title, description, priority);

        System.out.println("After adding new task: ");
        manager.listTask();
    }

    private static void handleDeleteTask(Scanner scanner, TaskManager manager) {
        int deleteId = readInt(
                scanner,
                "Enter task id to delete: ",
                "Invalid input. Please enter a valid task id."
        );
        manager.deleteTaskById(deleteId);
    }

    private static void handleUpdateTask(Scanner scanner, TaskManager manager) {
        int updateId = readInt(
                scanner,
                "Enter task id to update:",
                "Invalid input. Please enter a valid task id."
        );
        String newTitle;
        while (true) {
            System.out.println("Enter new title: ");
            newTitle = scanner.nextLine();

            if (!newTitle.isEmpty()) {
                break;
            }
            System.out.println("Title cannot be empty. Try again.");
        }

        String newDescription;
        while (true) {
            System.out.println("Enter new description: ");
            newDescription = scanner.nextLine();

            if (!newDescription.isEmpty()) {
                break;
            }
            System.out.println("Description cannot be empty. Try again.");
        }
        manager.updateTask(updateId, newTitle, newDescription);
    }
    private static void handleUpdatePriority(Scanner scanner, TaskManager manager) {
       int id = readInt(
               scanner,
               "Enter task id to update priority:",
               "Invalid input. Please enter a valid task id."
       );

        int newPriority = readPriority(scanner);
        manager.updateTaskPriority(id, newPriority);

    }
    private static void handleMarkAsCompleted(Scanner scanner, TaskManager manager) {
        int completedId = readInt(
                scanner,
                "Enter task id to mark as completed:",
                "Invalid input. Please enter a valid task id."
        );
        manager.markTaskAsCompleted(completedId);
    }

    private static void handleMarkAsInProgress(Scanner scanner, TaskManager manager) {
        int progressId  = readInt(
                scanner,
                "Enter task id to mark as IN_PROGRESS:",
                "Invalid input. Please enter a valid task id."
        );
        manager.markTaskAsInProgress(progressId);
    }

    private static int readMenuChoice(Scanner scanner) {
        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    private static int readInt(Scanner scanner, String message, String errorMessage){
        int value;

        while(true){
            System.out.println(message);

            if(scanner.hasNextInt()){
                value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }else{
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }
    }
    private static int readPriority(Scanner scanner){
        int value;
        while(true){
            value = readInt(
                    scanner,
                    "Enter priority (1-5):",
                    "Invalid input. Please enter a number."
            );
            if(value >= 1 && value <= 5){
                return value;
            }else{
                System.out.println("Priority must be between 1 and 5.");
            }
        }
    }
    private static String readNonEmptyString(Scanner scanner, String message){
        String value;

        while(true){
            System.out.println(message);
            value = scanner.nextLine();

            if(!value.trim().isEmpty()){
                return value;
            }else{
                System.out.println("Input cannot be empty. Try again.");
            }
        }
    }
}


