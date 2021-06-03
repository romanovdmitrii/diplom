package ru.dmitriiromanov.diplom.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dmitriiromanov.diplom.models.Contact;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    @Override
    List<Contact> findAll();
}
