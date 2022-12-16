package net.dudios.todolistmvc.task;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DbInit {

    private final TaskRepo taskRepo;

    //@EventListener(ApplicationReadyEvent.class)
    public void init() {
        taskRepo.save(new Task(1L, "Task 1", false, LocalDate.of(2020, 3, 15)));
        taskRepo.save(new Task(2L, "Task 2", true, LocalDate.of(2023, 11, 13)));
        taskRepo.save(new Task(3L, "Task 3", false, LocalDate.of(2022, 7, 17)));
        taskRepo.save(new Task(4L, "Task 4", true, LocalDate.of(2020, 1, 8)));
    }

}
