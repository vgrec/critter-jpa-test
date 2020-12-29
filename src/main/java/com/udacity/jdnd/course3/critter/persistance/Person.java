package com.udacity.jdnd.course3.critter.persistance;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @GeneratedValue
    @Id
    private long id;

    private String name;
}
