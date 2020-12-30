package com.udacity.jdnd.course3.critter.persistance.data;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;

@Entity
public class Pet {
    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @ManyToOne
    private Schedule schedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
