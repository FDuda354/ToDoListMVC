package net.dudios.todolistmvc.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.isDone = true and t.appUser.id = ?1 order by t.deadline")
    List<Task> findAllDoneTask(Long userId);

    @Query("select t from Task t where t.isDone = false and t.appUser.id = ?1 order by t.deadline")
    List<Task> findAllNotDoneTask(Long userId);

    @Query("select t from Task t where t.appUser.id = ?1 order by t.deadline")
    List<Task> findAllTask(Long userId);
}
