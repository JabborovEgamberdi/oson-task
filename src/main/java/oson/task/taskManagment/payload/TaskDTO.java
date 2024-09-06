package oson.task.taskManagment.payload;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @NotNull
    private String title;

    private String description;

    private LocalDateTime dueDate;

    private TaskStatus status;

}
