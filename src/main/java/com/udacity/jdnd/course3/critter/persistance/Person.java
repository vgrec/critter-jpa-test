package com.udacity.jdnd.course3.critter.persistance;

import javax.persistence.*;

@MappedSuperclass
public class Person {
    @GeneratedValue
    @Id
    @Column
    private long id;

    @Column
    private String name;
}
