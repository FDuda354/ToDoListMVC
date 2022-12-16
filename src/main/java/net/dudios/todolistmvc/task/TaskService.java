package net.dudios.todolistmvc.task;

import java.util.List;

public interface TaskService {
     List<Task> findAllTask();
     Task save(Task task);
     void delete(Long id);
     void update(Task task);
     void markAsDone(Long id);

     void markAsNotDone(Long id);

     List<Task> findAllDoneTask();

     List<Task> findAllNotDoneTask();
}
