package oson.task.taskManagment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oson.task.taskManagment.exception.TaskNotFoundException;
import oson.task.taskManagment.factory.TaskFactory;
import oson.task.taskManagment.model.Task;
import oson.task.taskManagment.payload.TaskDTO;
import oson.task.taskManagment.repo.TaskRepository;

import java.util.List;

@Slf4j
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Task findById(Integer taskId) {
        return this.taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException(String.format("Task not found with this id: %d", taskId))
        );
    }

    public Task save(TaskDTO taskDTO) {
        Task task = TaskFactory.createTask(taskDTO);
        return this.taskRepository.save(task);
    }

    public Task update(Integer taskId, TaskDTO taskDTO) {
        Task task = this.findById(taskId);
        task = TaskFactory.updateTask(task, taskDTO);
        return this.taskRepository.save(task);
    }

    public void delete(Integer taskId) {
        if (this.taskRepository.existsById(taskId)) {
            this.taskRepository.deleteById(taskId);
        } else {
            throw new TaskNotFoundException(String.format("Task not found with this id: %d", taskId));
        }
    }

}