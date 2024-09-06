package oson.task.taskManagment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import oson.task.taskManagment.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsById(Integer taskId);

}