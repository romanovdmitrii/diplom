package ru.dmitriiromanov.diplom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmitriiromanov.diplom.models.NewsModel;
import ru.dmitriiromanov.diplom.models.Role;
import ru.dmitriiromanov.diplom.models.User;
import ru.dmitriiromanov.diplom.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users/userList";
    }

    @GetMapping("/users/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/users";
        }

        Optional<User> user = userRepository.findById(id);
        ArrayList<User> list = new ArrayList<>();
        user.ifPresent(list::add);
        model.addAttribute("users", list);
        model.addAttribute("roles", Role.values());
        return "users/users_edit";
    }

    @PostMapping("/users/{id}/edit")
    public String blogUserEdit(
            @PathVariable(value = "id") long id,
            @RequestParam String username,
            @RequestParam String password,
            //@RequestParam String full_text,
            Model model) {

        User user = null;
        try {
            user = userRepository.findById(id).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Пользователь не найден");
        }

        user.setUsername(username);
        user.setPassword(password);
        //user.setFull_text(full_text);
        userRepository.save(user);
        return "redirect:/users";
    }
}
