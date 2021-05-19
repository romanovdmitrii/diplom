//package ru.dmitriiromanov.diplom.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import ru.dmitriiromanov.diplom.models.Role;
//import ru.dmitriiromanov.diplom.models.User;
//import ru.dmitriiromanov.diplom.repository.UserRepository;
//
//import java.util.Collections;
//
//@Controller
//public class RegistrationController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/registration")
//    public String registration() {
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String AddNewUser(User user, Model model) {
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//
//        if (userFromDB != null) {
//            //model.addAttribute("message", " User exists!");
//            return "/registration";
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepository.save(user);
//
//        return "redirect:/login";
//    }
//}
