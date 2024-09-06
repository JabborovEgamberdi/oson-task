package oson.task.taskManagment.validator;

import oson.task.taskManagment.payload.TaskDTO;

@FunctionalInterface
public interface Validator {

    void validate(TaskDTO taskDTO);

}