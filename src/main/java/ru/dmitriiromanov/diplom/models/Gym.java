package ru.dmitriiromanov.diplom.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    public Gym(){
    }

    public Gym(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
