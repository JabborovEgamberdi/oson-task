package oson.task.taskManagment.payload;

import lombok.Data;
import oson.task.taskManagment.model.Task;
import oson.task.taskManagment.model.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Task}
 */
@Data
public class TaskDTO implements Serializable {

    private String title;

    private String description;

    private LocalDateTime dueDate;

    private TaskStatus status;

}
