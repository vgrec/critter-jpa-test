package com.udacity.jdnd.course3.critter.persistance.data;

import javax.persistence.*;

/**
 * Super class for {@link Customer} and {@link Employee}.
 *
 * The inheritance relationship it's not reflected at the persistence level.
 * This has the effect of duplicating the ID and Name fields in both
 * Customer and Employee tables. On the plus side, the generated schema
 * is simpler, and for this example I chose simplicity first.
 */
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
