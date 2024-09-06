package oson.task.taskManagment.validator;

import org.springframework.stereotype.Service;
import oson.task.taskManagment.exception.ValidationException;
import oson.task.taskManagment.payload.TaskDTO;

@Service
public class DueDateValidator implements Validator {

    @Override
    public void validate(TaskDTO taskDTO) {
        if (taskDTO.getDueDate() == null) {
            throw new ValidationException("Due date is required");
        }
    }

}