package net.dudios.todolistmvc.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model) {
        Iterable<Task> tasks = taskService.findAllTask();
        model.addAttribute("allTasks", tasks);

        return "allTasks";
    }

    @PostMapping("newTask")
    public String addTask(@ModelAttribute Task task) {
        new Task();
        Task newTask = Task.builder()
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .isDone(false)
                .build();
        taskService.save(newTask);
        return "redirect:/tasks";
    }

    @GetMapping("newTaskPage")
    public String newTaskPage() {
        return "addTask";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
    @GetMapping("done/{id}")
    public String markAsDone(@PathVariable Long id) {
        taskService.markAsDone(id);
        return "redirect:/tasks";
    }
    @GetMapping("notDone/{id}")
    public String markAsNotDone(@PathVariable Long id) {
        taskService.markAsNotDone(id);
        return "redirect:/tasks";
    }

    @GetMapping("editPage/{id}")
    public String update(@PathVariable Long id) {

        return "editTask";
    }
    @GetMapping("edit")
    public String edit(@ModelAttribute Task task) {
        Task oldTask = taskService.findAllTask().stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));
        oldTask.setDescription(task.getDescription());
        oldTask.setDeadline(task.getDeadline());
        taskService.update(oldTask);
        return "redirect:/tasks";
    }
}
