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
    public List<Task> findAllDoneTask() {
        return taskRepo.findAllDoneTask();
    }

    @Override
    public List<Task> findAllNotDoneTask() {
        return taskRepo.findAllNotDoneTask();
    }

    @Override
    public Task save(Task task) {
        new Task();
        Task newTask = Task.builder()
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .isDone(false)
                .build();
        return taskRepo.save(newTask);
    }

    @Override
    public void delete(Long id) {
        taskRepo.findById(id).ifPresent(taskRepo::delete);
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
