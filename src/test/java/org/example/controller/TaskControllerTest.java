package org.example.controller;

import org.example.exception.TaskNotFoundException;
import org.example.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import org.springframework.http.MediaType;
import org.example.Task;
import java.util.List;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void getTasksReturnsOk() throws Exception {
        Task task = new Task("Test Task", "Test Description",1);
        when(taskService.getTasksWithPagination(ArgumentMatchers.any())).thenReturn(new PageImpl<>(List.of(task)));
        mockMvc.perform(get("/tasks")).andExpect(status().isOk());
    }

    @Test
    void getMissingTask() throws Exception {
        when(taskService.getTaskById(999L)).thenThrow(new TaskNotFoundException("Task not found with id: 999"));
        mockMvc.perform(get("/tasks/999")).andExpect(status().isNotFound());
    }

    @Test
    void updateTaskReturnOk() throws Exception {
        String requestBody = """
                {
                "title": "Updated Task",
                "description": "Updated description",
                "priority":2
                }
                """;
        mockMvc.perform(put("/tasks/1").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk());

    }

    @Test
    void createTaskReturnsCreated() throws Exception {
        String requestBody = """
                {
                "title": "New Task",
                "description": "Created from test",
                "priority":1
                }
                """;
        Task task = new Task("New Task","Created from test",1);
        when(taskService.addTask(anyString(),anyString(),anyInt())).thenReturn(task);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isCreated());
    }

    @Test
    void createdTaskReturnsBadRequest() throws Exception {
        String requestBody = """
                {
                "title": "",
                "description": "Invalid task",
                "priority": 1
                }
                """;
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isBadRequest());
    }

    @Test
    void deleteTaskReturnsNoContent() throws Exception{
        mockMvc.perform(delete("/tasks/1")).andExpect(status().isNoContent());
    }

    @Test
    void deleteMissingTaskReturnsNotFound() throws Exception{
        doThrow(new TaskNotFoundException("Task not found with id: 999")).when(taskService).deleteTaskById(999L);
        mockMvc.perform(delete("/tasks/999")).andExpect(status().isNotFound());
    }

    @Test
    void searchTasksReturnsOk() throws Exception{
        mockMvc.perform(get("/tasks/search").param("keyword","gym")).andExpect(status().isOk());
    }
}
