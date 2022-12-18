package net.dudios.todolistmvc.user;

import lombok.*;
import net.dudios.todolistmvc.task.Task;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Task> tasks;

}
