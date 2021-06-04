package ru.dmitriiromanov.diplom.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String coach;
    private String description;
    private String gym;

    public Training(){
    }

    public Training(Long id, String name, String coach, String description, String gym) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.description = description;
        this.gym = gym;
    }
}
