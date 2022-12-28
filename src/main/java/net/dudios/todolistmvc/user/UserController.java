package net.dudios.todolistmvc.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute AppUser user) {
        new AppUser();
        AppUser appUser = AppUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .tasks(new ArrayList<>())
                .build();
      userService.saveUser(appUser);
        return "redirect:/";
    }
}
