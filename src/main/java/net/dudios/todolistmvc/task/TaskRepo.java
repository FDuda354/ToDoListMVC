package net.dudios.todolistmvc.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {


    @Query(value = "SELECT * FROM tasks AS t WHERE t.is_done = true AND t.user_id = :userId ORDER BY t.deadline", nativeQuery = true)
    List<Task> findAllDoneTask(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM tasks AS t WHERE t.is_done = false AND t.user_id = :userId ORDER BY t.deadline", nativeQuery = true)
    List<Task> findAllNotDoneTask(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM tasks AS t WHERE t.user_id = :userId ORDER BY t.deadline", nativeQuery = true)
    List<Task> findAllTask(@Param("userId") Long userId);
}
