package org.example;

import org.example.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!console")
public class DataInitializer implements CommandLineRunner {

    private final TaskService taskService;

    public DataInitializer(TaskService taskService){
        this.taskService = taskService;
    }

    @Override
    public void run(String... args){
        taskService.addTask("Learn Spring Boot", "Create first REST endpoint",1);
        taskService.addTask("Practice REST API","Test GET endpoint in browser",2);
        taskService.addTask("Prepare portfolio","Push project changes to GitHub",3);
    }
}

