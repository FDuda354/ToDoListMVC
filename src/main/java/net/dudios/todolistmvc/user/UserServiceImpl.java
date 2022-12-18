package net.dudios.todolistmvc.user;

import lombok.AllArgsConstructor;
import net.dudios.todolistmvc.task.Task;
import net.dudios.todolistmvc.task.TaskRepo;
import net.dudios.todolistmvc.task.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Override
    public void saveUser(AppUser appUser) {
        if(!userRepo.existsById(appUser.getId()))
            userRepo.save(appUser);
        else
            throw new IllegalStateException("User with id " + appUser.getId() + " already exists");
    }

    @Override
    public AppUser findById(Long id) {
       return userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.findById(id).ifPresent(userRepo::delete);
    }

}
