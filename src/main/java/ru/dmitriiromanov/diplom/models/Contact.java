package ru.dmitriiromanov.diplom.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String contactInfo;

    public Contact(){
    }

    public Contact(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
