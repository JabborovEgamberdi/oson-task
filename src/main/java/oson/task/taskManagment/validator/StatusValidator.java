package oson.task.taskManagment.validator;

import org.springframework.stereotype.Service;
import oson.task.taskManagment.exception.InvalidStatusException;
import oson.task.taskManagment.model.TaskStatus;
import oson.task.taskManagment.payload.TaskDTO;

@Service
public class StatusValidator implements Validator {

    @Override
    public void validate(TaskDTO taskDTO) {
        if (taskDTO.getStatus() != null) {
            determineStatus(taskDTO.getStatus());
        }
    }

    private void determineStatus(TaskStatus status) {
        switch (status) {
            case IN_PROGRESS:
            case COMPLETED:
            case OPEN:
            default:
                throw new InvalidStatusException("Invalid status " + status);
        }
    }

}