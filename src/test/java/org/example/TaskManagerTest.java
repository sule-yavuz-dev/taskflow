package org.example;

import org.example.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskManagerTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskManager taskManager;

    @Test
    void addTaskSavesTask(){
        Task task = new Task("Test Task", "Test Description",1);

        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task savedTask = taskManager.addTask("Test Task","Test Description",1);
        assertEquals("Test Task", savedTask.getTitle());
        assertEquals("Test Description", savedTask.getDescription());
        assertEquals(1,savedTask.getPriority());
    }

    @Test
    void updateTaskPriorityUpdatesPriority(){
        Task task = new Task("Test task", "Test Description",3);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskManager.updateTaskPriority(1L,1);
        assertEquals(1,task.getPriority());
    }

    @Test
    void deleteTaskByIdDeleteTask(){
        Task task = new Task("Test Task", "Test Description",1);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        taskManager.deleteTaskById(1L);
        verify(taskRepository).delete(task);
    }

    @Test
    void getTaskByIdThrowsExceptionWhenTaskNotFound() {
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> {
            taskManager.getTaskById(999L);
        });
    }

    @Test
    void markTaskAsCompletedChangesStatus(){
        Task task = new Task("Finish API", "Complete service tests",2);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskManager.markTaskAsCompleted(1L);
        assertEquals(TaskStatus.COMPLETED, task.getStatus());
        verify(taskRepository).save(task);

    }
}
