package com.udacity.jdnd.course3.critter.persistance.data;

import javax.persistence.*;

@MappedSuperclass
public class Person {
    @GeneratedValue
    @Id
    @Column
    private long id;

    @Column
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
