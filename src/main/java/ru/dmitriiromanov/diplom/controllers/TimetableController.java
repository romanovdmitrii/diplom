package ru.dmitriiromanov.diplom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimetableController {
    @GetMapping("/timetable")
    public String timetable(Model model) {
        model.addAttribute("title", "Расписание");
        return "timetable.html";
    }

    @GetMapping("/training")
    public String training(Model model) {
        model.addAttribute("title", "Групповые программы");
        return "training.html";
    }
}
