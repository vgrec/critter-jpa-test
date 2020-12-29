package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.Optional;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/test")
    public String test() throws Throwable {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Vanea Cociuliiskii");
        dto.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));
        dto.setDaysAvailable(Sets.newHashSet(DayOfWeek.WEDNESDAY, DayOfWeek.TUESDAY));

        employeeRepository.save(toEmployee(dto));


        Optional<Employee> o = employeeRepository.findById(1L);
        if (o.isPresent()) {
            Employee p = o.get();
            System.out.println("id: " + p.getId());
            System.out.println("name: " + p.getName());
            System.out.println("skills: " + p.getSkills());
            System.out.println("days: " + p.getDaysAvailable());
        } else {
            System.out.println("Could not get");
        }


        return "Critter Starter installed successfully";
    }

    private Employee toEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSkills(dto.getSkills());
        employee.setDaysAvailable(dto.getDaysAvailable());

//        for (EmployeeSkill skill : dto.getSkills())
//        employee.setSkills();

        return employee;

    }
}
