package ru.dmitriiromanov.diplom.controllers;

import org.apache.el.stream.Stream;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dmitriiromanov.diplom.models.NewsModel;
import ru.dmitriiromanov.diplom.repository.NewsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/news")
    public String news(Model model) {
        List<NewsModel> list = newsRepository.findAll();
        Collections.reverse(list);
        model.addAttribute("news", list);
        return "news/news";
    }

    @GetMapping("/news/add")
    public String newsAdd(Model model) {
        Iterable<NewsModel> newsModels = newsRepository.findAll();
        model.addAttribute("news", newsModels);
        return "news/news_add";
    }

    @PostMapping("/news/add")
    public String blogNewsAdd(
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            Model model) {
        NewsModel news = new NewsModel(title, anons, full_text);
        newsRepository.save(news);
        return "redirect:/news";
    }

    @GetMapping("/news/{id}")
    public String newsDetails(@PathVariable(value = "id") long id, Model model) {
        if (!newsRepository.existsById(id)) {
            return "redirect:/news";
        }

        Optional<NewsModel> newsModel = newsRepository.findById(id);
        ArrayList<NewsModel> list = new ArrayList<>();
        newsModel.ifPresent(list::add);
        model.addAttribute("news", list);
        return "news/news_details";
    }

    @GetMapping("/news/{id}/edit")
    public String newsEdit(@PathVariable(value = "id") long id, Model model) {
        if (!newsRepository.existsById(id)) {
            return "redirect:/news";
        }

        Optional<NewsModel> newsModel = newsRepository.findById(id);
        ArrayList<NewsModel> list = new ArrayList<>();
        newsModel.ifPresent(list::add);
        model.addAttribute("news", list);
        return "news/news_edit";
    }

    @PostMapping("/news/{id}/edit")
    public String blogNewsEdit(
            @PathVariable(value = "id") long id,
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            Model model) {

        NewsModel news = null;
        try {
            news = newsRepository.findById(id).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Новость не найдена");
        }

        news.setTitle(title);
        news.setAnons(anons);
        news.setFull_text(full_text);
        newsRepository.save(news);
        return "redirect:/news";
    }

    @PostMapping("/news/{id}/delete")
    public String blogNewsDelete(
            @PathVariable(value = "id") long id,
            Model model
    ) {
        NewsModel news = null;
        try {
            news = newsRepository.findById(id).orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return null;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Новость не найдена");
        }
        assert news != null;
        newsRepository.delete(news);
        return "redirect:/news";
    }
}
