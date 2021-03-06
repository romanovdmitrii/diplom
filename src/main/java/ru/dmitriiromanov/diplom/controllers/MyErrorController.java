package ru.dmitriiromanov.diplom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String home(Model model) {
        System.out.println("ALARM");
        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
