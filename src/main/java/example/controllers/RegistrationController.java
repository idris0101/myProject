package example.controllers;

import example.entities.Role;
import example.entities.User;
import example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

     @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user, Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.addAttribute("message", "User exists!");
            return "registration";
        }else {
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
        }
        return "redirect:/login";
    }
}
