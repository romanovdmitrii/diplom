package ru.dmitriiromanov.diplom.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriiromanov.diplom.models.NewsModel;

import java.util.List;

public interface NewsRepository extends CrudRepository<NewsModel, Long> {
    @Override
    List<NewsModel> findAll();
}
