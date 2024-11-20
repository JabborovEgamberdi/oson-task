package oson.task.taskManagment.repo;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import oson.task.taskManagment.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsById(@NotNull @Param("taskId") Integer taskId);

}