package net.dudios.todolistmvc.user;

public interface UserService {
    void saveUser(AppUser appUser);
    AppUser findById(Long id);

    void deleteUser(Long id);

}
