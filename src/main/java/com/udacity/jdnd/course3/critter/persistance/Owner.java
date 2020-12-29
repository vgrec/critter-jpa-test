package com.udacity.jdnd.course3.critter.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Owner {
    @GeneratedValue
    @Id
    private Long id;

    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany
    private List<Pet> pets;
}
