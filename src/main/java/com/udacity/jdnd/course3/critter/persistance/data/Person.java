package com.udacity.jdnd.course3.critter.persistance.data;

import javax.persistence.*;

@MappedSuperclass
public class Person {
    @GeneratedValue
    @Id
    @Column
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
