package net.dudios.todolistmvc.task;

import lombok.AllArgsConstructor;
import net.dudios.todolistmvc.user.AppUser;
import net.dudios.todolistmvc.user.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Override
    public List<Task> findAllTask(Long userId) {
        return  taskRepo.findAllTask(userId);
    }
    @Override
    public List<Task> findAllDoneTask(Long userId) {
        return  taskRepo.findAllDoneTask(userId);
    }

    @Override
    public List<Task> findAllNotDoneTask(Long userId) {
        return  taskRepo.findAllNotDoneTask(userId);
    }

    @Override
    public Task save(Long userId,Task task) {
        AppUser user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        new Task();
        Task newTask = Task.builder()
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .isDone(false)
                .appUser(user)
                .build();
        user.getTasks().add(newTask);
        taskRepo.save(newTask);
        userRepo.save(user);
        return newTask;
    }

    @Override
    public void delete(Long taskId) {
        taskRepo.findById(taskId).ifPresent(taskRepo::delete);
    }


    @Override
    public void markAsDone(Long taskId) {
        taskRepo.findById(taskId).ifPresent(t -> {
            t.setDone(true);
            taskRepo.save(t);
        });
    }

    @Override
    public void markAsNotDone(Long taskId) {
        taskRepo.findById(taskId).ifPresent(t -> {
            t.setDone(false);
            taskRepo.save(t);
        });
    }


}
