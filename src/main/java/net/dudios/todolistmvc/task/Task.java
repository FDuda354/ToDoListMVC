package net.dudios.todolistmvc.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.dudios.todolistmvc.user.AppUser;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tasks")
public class Task implements Comparable<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean isDone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser appUser;

    public Task(Long id, String description, boolean isDone, LocalDate deadline) {
        this.id = id;
        this.description = description;
        this.isDone = isDone;
        this.deadline = deadline;
    }

    @Override
    public int compareTo(Task task) {
        return this.deadline.compareTo(task.deadline);
    }
}
