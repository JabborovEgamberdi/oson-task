package oson.task.taskManagment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oson.task.taskManagment.payload.TaskDTO;
import oson.task.taskManagment.service.TaskService;
import oson.task.taskManagment.validator.TaskValidator;

import java.util.List;

@Slf4j
@RestController("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final List<TaskValidator> validators;

    public TaskController(
            TaskService taskService,
            List<TaskValidator> validators
    ) {
        this.taskService = taskService;
        this.validators = validators;
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        try {
            return ResponseEntity.ok(taskService.findAll());
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{taskId}")
    public HttpEntity<?> getById(@PathVariable Integer taskId) {
        try {
            return ResponseEntity.ok(taskService.findById(taskId));
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody TaskDTO taskDTO) {
        try {
            this.validators.forEach(validator -> validator.validate(taskDTO));
            return ResponseEntity.status(201).body(taskService.save(taskDTO));
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{taskId}")
    public HttpEntity<?> update(
            @PathVariable Integer taskId,
            @RequestBody TaskDTO taskDTO
    ) {
        try {
            this.validators.forEach(validator -> validator.validate(taskDTO));
            return ResponseEntity.status(201).body(taskService.update(taskId, taskDTO));
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{taskId}")
    public HttpEntity<?> delete(@PathVariable Integer taskId) {
        try {
            taskService.delete(taskId);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.badRequest().build();
        }
    }

}