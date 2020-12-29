package com.udacity.jdnd.course3.critter.persistance.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Employee extends Person {
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Skill> skills;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<DayAvailable> daysAvailable;

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<DayAvailable> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayAvailable> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
