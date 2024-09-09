package oson.task.taskManagment.factory;

import oson.task.taskManagment.model.Task;
import oson.task.taskManagment.payload.TaskDTO;

public class TaskFactory {

    public static Task createTask(TaskDTO taskDTO) {
        return new Task(taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.getDueDate());
    }

}
