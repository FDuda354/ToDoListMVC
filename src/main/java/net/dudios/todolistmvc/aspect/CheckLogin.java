package net.dudios.todolistmvc.aspect;

import net.dudios.todolistmvc.user.AppUser;
import net.dudios.todolistmvc.user.UserRepo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckLogin {

    private final UserRepo userRepo;

    public CheckLogin(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Around("@annotation(LoginAspect)")
    private Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        AppUser user = (AppUser) args[0];
        AppUser appUser = userRepo.findByUsername(user.getUsername()).orElse(null);
        if (appUser!=null && appUser.getPassword().equals(user.getPassword()))
            return joinPoint.proceed();
        else
            return null;
    }

}
