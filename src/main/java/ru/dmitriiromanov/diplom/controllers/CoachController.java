package ru.dmitriiromanov.diplom.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dmitriiromanov.diplom.models.Coach;
import ru.dmitriiromanov.diplom.models.NewsModel;
import ru.dmitriiromanov.diplom.models.Role;
import ru.dmitriiromanov.diplom.models.User;
import ru.dmitriiromanov.diplom.repository.CoachRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class CoachController {

    private final CoachRepository coachRepository;

    public CoachController(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @GetMapping("/coaches")
    public String coachesList(Model model) {
        Iterable<Coach> coaches = coachRepository.findAll();
        model.addAttribute("coaches", coaches);
        return "coaches/coachesList";
    }

    @GetMapping("/coaches/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String coachesAdd(Model model) {
        Iterable<Coach> coaches = coachRepository.findAll();
        model.addAttribute("coaches", coaches);
        return "coaches/coaches_add";
    }

    @PostMapping("/coaches/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String blogCoachesAdd(
            @RequestParam String name,
            @RequestParam String lastname,
            @RequestParam String phone,
            @RequestParam String position,
            @RequestParam String description,
            Model model) {
        Coach coach = new Coach(name, lastname, phone, position, description);
        coachRepository.save(coach);
        return "redirect:/coaches";
    }

    @GetMapping("/coaches/{id}/edit")
    public String coachesEdit(@PathVariable(value = "id") long id, Model model) {
        if (!coachRepository.existsById(id)) {
            return "redirect:/coaches";
        }

        Optional<Coach> coach = coachRepository.findById(id);
        ArrayList<Coach> list = new ArrayList<>();
        coach.ifPresent(list::add);
        model.addAttribute("coach", list);
        return "coaches/coaches_edit";
    }

    @PostMapping("/coaches/{id}/edit")
    public String blogCoachesEdit(
            @PathVariable(value = "id") long id,
            @RequestParam String name,
            @RequestParam String lastname,
            @RequestParam String phone,
            @RequestParam String position,
            @RequestParam String description,
            Model model) {

        Coach coach = null;
        try {
            coach = coachRepository.findById(id).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Тренер не найден");
        }

        coach.setName(name);
        coach.setLastname(lastname);
        coach.setPhone(phone);
        coach.setPosition(position);
        coach.setDescription(description);
        coachRepository.save(coach);
        return "redirect:/coaches";
    }

    @PostMapping("/coaches/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String coachesDelete(
            @PathVariable(value = "id") long id,
            Model model
    ) {
        Coach coach = null;
        try {
            coach = coachRepository.findById(id).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Тренер не найден");
        }
        assert coach != null;
        coachRepository.delete(coach);
        return "redirect:/coaches";
    }
}
