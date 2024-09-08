package oson.task.taskManagment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import oson.task.taskManagment.model.Task;
import oson.task.taskManagment.model.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Task}
 */
@Data
@AllArgsConstructor
public class TaskDTO implements Serializable {

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private TaskStatus status;

    public TaskDTO(String title, String description, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}
