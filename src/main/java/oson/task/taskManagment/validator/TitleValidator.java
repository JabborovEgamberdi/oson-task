package oson.task.taskManagment.validator;

import org.springframework.stereotype.Service;
import oson.task.taskManagment.exception.ValidationException;
import oson.task.taskManagment.payload.TaskDTO;

@Service
public class TitleValidator implements Validator {

    @Override
    public void validate(TaskDTO taskDTO) {
        if (taskDTO.getTitle().isEmpty() || taskDTO.getTitle().isBlank()) {
            throw new ValidationException("Title is required");
        }
    }

}