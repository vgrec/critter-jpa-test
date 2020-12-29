package com.udacity.jdnd.course3.critter.persistance.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @GeneratedValue
    @Id
    private Long id;

    private LocalDateTime date;

    @OneToMany
    private List<Employee> employees;

    @OneToMany
    private List<Pet> pets;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }
}
