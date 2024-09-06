package oson.task.taskManagment.validator;

import oson.task.taskManagment.payload.TaskDTO;

@FunctionalInterface
public interface TaskValidator {

    void validate(TaskDTO taskDTO);

}