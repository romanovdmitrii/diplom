//package ru.dmitriiromanov.diplom.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import ru.dmitriiromanov.diplom.models.User;
//import ru.dmitriiromanov.diplom.repository.UserRepository;
//
//@Controller
//public class UserController {
//
//    private final UserRepository userRepository;
//
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping("/users")
//    public String userList(Model model) {
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        return "userList";
//    }
//
//}
