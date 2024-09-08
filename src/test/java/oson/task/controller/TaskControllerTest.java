package oson.task.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import oson.task.taskManagment.controller.TaskController;
import oson.task.taskManagment.model.Task;
import oson.task.taskManagment.payload.TaskDTO;
import oson.task.taskManagment.service.TaskService;
import oson.task.taskManagment.validator.TaskValidator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private List<TaskValidator> taskValidators;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setId(1);
        task.setTitle("Task 1");
        task.setDescription("Task 1");
        task.setDueDate(LocalDateTime.now());
    }

    @DisplayName("Test for getAll() API method")
    @Test
    public void getAll() {
        when(taskService.findAll()).thenReturn(Collections.singletonList(task));
        ResponseEntity<List<Task>> response = taskController.getAll();
        assertNotNull(response.getBody());
        List<Task> taskList = response.getBody();
        assertEquals(1, taskList.size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("Test for getById() API method")
    @Test
    public void getById() {
        when(taskService.findById(1)).thenReturn(task);
        ResponseEntity<Task> response = taskController.getById(1);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task.getId(), response.getBody().getId());
    }

    @DisplayName("Test for save() API method")
    @Test
    public void save() {
        TaskDTO taskDTO = new TaskDTO(task.getTitle(), task.getDescription(), task.getDueDate());
        doNothing().when(taskValidators).forEach(any());
        when(taskService.save(taskDTO)).thenReturn(task);
        ResponseEntity<Task> response = taskController.save(taskDTO);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task.getId(), response.getBody().getId());
    }

    @DisplayName("Test for update() API method")
    @Test
    public void update() {
        TaskDTO taskDTO = new TaskDTO(task.getTitle(), "Task 1 updated", task.getDueDate());
        when(taskService.update(1, taskDTO)).thenReturn(task);
        doNothing().when(taskValidators).forEach(any());
        ResponseEntity<Task> response = taskController.update(1, taskDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(taskDTO.getDescription(), "Task 1 updated");
    }

}