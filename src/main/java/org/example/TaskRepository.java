package org.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByPriority(int priority);

    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

}
