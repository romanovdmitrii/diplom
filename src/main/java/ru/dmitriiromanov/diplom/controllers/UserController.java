package ru.dmitriiromanov.diplom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmitriiromanov.diplom.models.Role;
import ru.dmitriiromanov.diplom.models.User;
import ru.dmitriiromanov.diplom.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
//@Secured("ADMIN")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEdit(@PathVariable(value = "id") long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/users";
        }

        Optional<User> user = userRepository.findById(id);
        ArrayList<User> list = new ArrayList<>();
        user.ifPresent(list::add);
        model.addAttribute("users", list);
        model.addAttribute("roles", Role.ADMIN);
        return "users/users_edit";
    }

    @PostMapping("/users/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String blogUserEdit(
            @PathVariable(value = "id") long id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam(required = false) boolean roleAdmin,
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
        user.setPhone(phone);
        if (roleAdmin) {
            user.getRoles().add(Role.ADMIN);
        } else {
            if (user.getRoles().contains(Role.ADMIN)) {
                user.getRoles().remove(Role.ADMIN);
            }
        }
        user.setAdmin(roleAdmin);
        userRepository.save(user);
        return "redirect:/users";
    }
}
