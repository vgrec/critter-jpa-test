package com.udacity.jdnd.course3.critter.persistance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {
    @GeneratedValue
    @Id
    private Long id;

    private String name;
}
