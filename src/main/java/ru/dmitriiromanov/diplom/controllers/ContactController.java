package ru.dmitriiromanov.diplom.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dmitriiromanov.diplom.models.Contact;
import ru.dmitriiromanov.diplom.models.NewsModel;
import ru.dmitriiromanov.diplom.repository.ContactRepository;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

@Controller
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contact")
    public String contactInfo(Model model) {
        List<Contact> list = contactRepository.findAll();
        Collections.reverse(list);
        model.addAttribute("contacts", list);
        return "/contact";
    }

    @PostMapping("/contact/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String contactDelete(
            @PathVariable(value = "id") long id,
            Model model
    ) {
        Contact contact = null;
        try {
            contact = contactRepository.findById(id).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Заявка не найдена");
        }
        assert contact != null;
        contactRepository.delete(contact);
        return "redirect:/contact";
    }
}
