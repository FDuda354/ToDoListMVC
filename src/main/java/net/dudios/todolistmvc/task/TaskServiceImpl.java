package net.dudios.todolistmvc.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @Override
    public List<Task> findAllTask() {
        return taskRepo.findAllTask();
    }

    @Override
    public Task save(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public void delete(Long id) {
        taskRepo.findById(id).ifPresent(taskRepo::delete);
    }

    @Override
    public void update(Task task) {
        taskRepo.findById(task.getId()).ifPresent(t -> {
            t.setDone(task.isDone());
            t.setDescription(task.getDescription());
            t.setDeadline(task.getDeadline());
            taskRepo.save(t);
        });
    }

    @Override
    public void markAsDone(Long id) {
        taskRepo.findById(id).ifPresent(t -> {
            t.setDone(true);
            taskRepo.save(t);
        });
    }

    @Override
    public void markAsNotDone(Long id) {
        taskRepo.findById(id).ifPresent(t -> {
            t.setDone(false);
            taskRepo.save(t);
        });
    }
}
