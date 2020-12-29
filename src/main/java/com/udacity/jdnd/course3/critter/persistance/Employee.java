package com.udacity.jdnd.course3.critter.persistance;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Employee extends Person {
    @OneToMany
    private Set<Skill> skills;

    @OneToMany
    private Set<DayAvailable> daysAvailable;
}
