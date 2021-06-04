package ru.dmitriiromanov.diplom.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String position;
    private String name;
    private String lastname;
    private String phone;
    private String description;

    public Coach(){
    }

    public Coach(Long id, String position, String name, String lastname, String phone, String description) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.description = description;
    }
}
