package net.dudios.todolistmvc.task;

import net.dudios.todolistmvc.user.AppUser;
import net.dudios.todolistmvc.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;
    private Long userId;
    private String lastPage;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.findAllTask(this.userId).stream().sorted().toList();
        model.addAttribute("allTasks", tasks);
        lastPage = "";
        return "allTasks";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute AppUser user) {
        AppUser appUser = userService.login(user);
        if (appUser == null)
            return "redirect:/";
        this.userId = appUser.getId();
        return "redirect:/tasks";

    }

    @GetMapping("done")
    public String doneTaskPage(Model model) {
        List<Task> tasks = taskService.findAllDoneTask(this.userId);
        model.addAttribute("allDoneTasks", tasks);
        lastPage = "done";
        return "doneTask";
    }

    @GetMapping("notDone")
    public String NotDoneTaskPage(Model model) {
        List<Task> tasks = taskService.findAllNotDoneTask(this.userId);
        model.addAttribute("allNotDoneTasks", tasks);
        lastPage = "notDone";
        return "notDoneTask";
    }

    @PostMapping("newTask")
    public String addTask(@ModelAttribute Task task) {
        taskService.save(this.userId, task);
        return "redirect:/tasks";
    }

    @GetMapping("newTaskPage")
    public String newTaskPage() {
        return "addTask";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks/" + lastPage;
    }

    @GetMapping("done/{taskId}")
    public String markAsDone(@PathVariable Long taskId) {
        taskService.markAsDone(taskId);
        return "redirect:/tasks/" + lastPage;

    }

    @GetMapping("notDone/{taskId}")
    public String markAsNotDoneLong(@PathVariable Long taskId) {
        taskService.markAsNotDone(taskId);
        return "redirect:/tasks/" + lastPage;

    }
}