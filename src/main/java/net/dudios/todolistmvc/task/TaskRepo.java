package net.dudios.todolistmvc.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    @Query("select t from Task t order by t.id")
    List<Task> findAllTask();
}
