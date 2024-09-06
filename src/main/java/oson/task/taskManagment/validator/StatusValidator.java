package oson.task.taskManagment.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oson.task.taskManagment.exception.InvalidStatusException;
import oson.task.taskManagment.model.TaskStatus;
import oson.task.taskManagment.payload.TaskDTO;

@Slf4j
@Service
public class StatusValidator implements TaskValidator {

    @Override
    public void validate(TaskDTO taskDTO) {
        if (taskDTO.getStatus() != null) {
            checkStatus(taskDTO.getStatus());
        }
    }

    private void checkStatus(TaskStatus status) {
        switch (status) {
            case IN_PROGRESS, COMPLETED, OPEN:
                return;
            default:
                throw new InvalidStatusException("Invalid status " + status);
        }
    }

}