package org.example;

import java.util.List;

public class MenuHandler {
    public void showMenu() {
        System.out.println("\n=== Task Manager ===");

        List<String> menuItems = List.of(
        "1.Add task",
        "2.List tasks",
        "3.Delete task",
        "4.Update task",
        "5.Mark as completed",
        "6.List completed tasks",
        "7.List pending tasks",
        "8.Mark as in progress",
        "9.List tasks by priority",
        "10.Update task priority",
        "11.Search tasks",
        "12.Sort tasks by priority",
        "0.Exit"
    );
        for(String item : menuItems){
            System.out.println(item);
        }
        System.out.println("Choose an option: ");
    }
}