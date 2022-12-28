package net.dudios.todolistmvc.user;

import net.dudios.todolistmvc.aspect.LoginAspect;


public interface UserService {
    void saveUser(AppUser appUser);
    AppUser findById(Long id);

    void deleteUser(Long id);

    AppUser findByUsername(String username);

    @LoginAspect
    AppUser login(AppUser user);
}
