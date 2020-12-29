package com.udacity.jdnd.course3.critter.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Employee {
    @GeneratedValue
    @Id
    private long id;

    private String name;

    @OneToMany
    private Set<Skill> skills;

    @OneToMany
    private Set<Day> daysAvailable;
}
