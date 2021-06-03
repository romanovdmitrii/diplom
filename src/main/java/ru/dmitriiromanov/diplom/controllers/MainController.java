package ru.dmitriiromanov.diplom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dmitriiromanov.diplom.models.Contact;
import ru.dmitriiromanov.diplom.models.NewsModel;
import ru.dmitriiromanov.diplom.repository.ContactRepository;

@Controller
public class MainController {

    private final ContactRepository contactRepository;

    public MainController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home.html";
    }

    @PostMapping("/")
    public String contact(
            @RequestParam String contact,
            Model model) {
        Contact contactInfo = new Contact(contact);
        System.out.println(contact);
        contactRepository.save(contactInfo);
        return "home.html";
    }

    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("title", "Команда");
        return "team.html";
    }

    @GetMapping("/adminPanel")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminPanel(Model model) {
        model.addAttribute("title", "Панель администрирования");
        return "adminPanel.html";
    }
}