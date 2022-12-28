package net.dudios.todolistmvc.task;

import lombok.AllArgsConstructor;
import net.dudios.todolistmvc.user.AppUser;
import net.dudios.todolistmvc.user.UserRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class DbInit {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

   @EventListener(ApplicationReadyEvent.class)
    public void init() {

        new AppUser();
        AppUser user = AppUser.builder()
                .username("user")
                .password("user")
                .tasks(new ArrayList<>())
                .build();
        Task task1 = new Task(1L, "Task 1", false, LocalDate.of(2020, 3, 15));
        Task task2 = new Task(2L, "Task 2", true, LocalDate.of(2023, 11, 13));
        Task task3 = new Task(3L, "Task 3", false, LocalDate.of(2022, 7, 17));
        Task task4 = new Task(4L, "Task 4", true, LocalDate.of(2020, 1, 8));
        userRepo.save(user);
        task1.setAppUser(user);
        task2.setAppUser(user);
        task3.setAppUser(user);
        task4.setAppUser(user);


        user.getTasks().add(task1);
        user.getTasks().add(task2);
        user.getTasks().add(task3);
        user.getTasks().add(task4);

        taskRepo.save(task1);
        taskRepo.save(task2);
        taskRepo.save(task3);
       taskRepo.save(task4);
        userRepo.save(user);

    }

}
