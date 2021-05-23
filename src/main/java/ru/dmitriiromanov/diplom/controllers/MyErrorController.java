package ru.dmitriiromanov.diplom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyErrorController implements ErrorController {

    private final MainController mainController;


    @RequestMapping("/error")
    public String home(Model model) {
        System.out.println("ALARM");
        return mainController.home(model);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
