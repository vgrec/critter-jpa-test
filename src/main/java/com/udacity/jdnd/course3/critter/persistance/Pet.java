package com.udacity.jdnd.course3.critter.persistance;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pet {
    @GeneratedValue
    @Id
    private Long id;

    private PetType type;
    private String name;

    @OneToOne
    private Owner owner;
}
