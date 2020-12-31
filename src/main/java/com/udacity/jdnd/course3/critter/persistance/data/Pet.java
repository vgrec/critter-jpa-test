package com.udacity.jdnd.course3.critter.persistance.data;

import com.udacity.jdnd.course3.critter.pet.PetType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pet {
    @GeneratedValue
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "pets")
    private List<Schedule> schedules;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
