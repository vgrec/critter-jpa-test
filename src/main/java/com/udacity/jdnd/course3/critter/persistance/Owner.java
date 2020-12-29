package com.udacity.jdnd.course3.critter.persistance;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Owner extends Person {
    private String phoneNumber;
    private String notes;

    @OneToMany
    private List<Pet> pets;
}
