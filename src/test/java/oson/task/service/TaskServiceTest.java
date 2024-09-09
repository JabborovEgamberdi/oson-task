package oson.task.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import oson.task.taskManagment.model.Task;
import oson.task.taskManagment.payload.TaskDTO;
import oson.task.taskManagment.repo.TaskRepository;
import oson.task.taskManagment.service.TaskService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task();
        task.setId(1);
        task.setTitle("Task 1");
        task.setDescription("Task 1");
        task.setDueDate(LocalDateTime.now());
    }

    @DisplayName("Test findAll() service method")
    @Test
    public void findAll() {
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));
        assertEquals(1, taskService.findAll().size());
    }

    @DisplayName("Test findById() service method")
    @Test
    public void findById() {
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        task = taskService.findById(1);
        assertNotNull(task);
        assertEquals(1, task.getId());
    }

    @DisplayName("Test save() service method")
    @Test
    public void save() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task saved = taskService.save(new TaskDTO(task.getTitle(), task.getDescription(), task.getDueDate()));
        assertEquals(task.getTitle(), saved.getTitle());
    }

    @DisplayName("Test update() service method")
    @Test
    public void update() {
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        TaskDTO taskDTO = new TaskDTO(task.getTitle(), "Task 1 Test", task.getDueDate());
        Task updatedTask = taskService.update(1, taskDTO);
        assertNotNull(updatedTask);
        assertEquals(updatedTask.getDescription(), "Task 1 Test");
        verify(taskRepository).save(task);
    }

    @DisplayName("Test delete() service method")
    @Test
    public void delete() {
        when(taskRepository.existsById(1)).thenReturn(true);
        taskService.delete(1);
        verify(taskRepository, times(1)).deleteById(1);
    }

}