package net.dudios.todolistmvc.task;

import java.util.List;

public interface TaskService {
     List<Task> findAllTask(Long userId);
     Task save(Long userId,Task task);
     void delete(Long taskId);
     void markAsDone(Long taskId);

     void markAsNotDone(Long taskId);

     List<Task> findAllDoneTask(Long userId);

     List<Task> findAllNotDoneTask(Long userId);
}
