package ru.dmitriiromanov.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmitriiromanov.diplom.models.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
