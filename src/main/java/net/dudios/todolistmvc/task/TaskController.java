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
        List<Task> tasks = taskService.findAllTask();
        model.addAttribute("allTasks", tasks);

        return "allTasks";
    }
    @GetMapping("done")
    public String doneTaskPage(Model model) {
        List<Task> tasks = taskService.findAllDoneTask();
        model.addAttribute("allDoneTasks", tasks);
        return "doneTask";
    }
    @GetMapping("notDone")
    public String NotDoneTaskPage(Model model) {
        List<Task> tasks = taskService.findAllNotDoneTask();
        model.addAttribute("allNotDoneTasks", tasks);
        return "notDoneTask";
    }

    @PostMapping("newTask")
    public String addTask(@ModelAttribute Task task) {

        taskService.save(task);
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
        taskService.update(task);
        return "redirect:/tasks";
    }
}