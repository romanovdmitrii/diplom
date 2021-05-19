package ru.dmitriiromanov.diplom.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriiromanov.diplom.models.NewsModel;

public interface NewsRepository extends CrudRepository<NewsModel, Long> {
}
