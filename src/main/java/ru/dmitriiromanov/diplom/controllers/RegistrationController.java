package ru.dmitriiromanov.diplom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dmitriiromanov.diplom.models.Role;
import ru.dmitriiromanov.diplom.models.User;
import ru.dmitriiromanov.diplom.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String AddNewUser(User user, Model model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        // проверка на сужествование пользователя в базе
        if (userFromDB != null) {
            //model.addAttribute("message", " User exists!");
            return "/registration";
        }

        // проверка на заполненность полей
        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return "/registration";
        }

        // добавление роли USER и сохранение в базе
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/users";
    }
}
