package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.service.PersonService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/test")
    public String test() throws Throwable {
        EmployeeDTO mariana = new EmployeeDTO();
        mariana.setName("Mariana");
        mariana.setDaysAvailable(Sets.newHashSet(DayOfWeek.WEDNESDAY, DayOfWeek.TUESDAY));
        mariana.setSkills(Sets.newHashSet(EmployeeSkill.SHAVING, EmployeeSkill.PETTING));


        PetDTO cat = new PetDTO();
        cat.setType(PetType.CAT);
        cat.setName("Samira");
        cat.setOwnerId(mariana.getId());


        System.out.println("Found By skill...");

        HashSet<EmployeeSkill> skills = Sets.newHashSet(EmployeeSkill.SHAVING);
        DayOfWeek dayOfWeek = DayOfWeek.TUESDAY;
        List<Employee> results = employeeRepository.findEmployeesBySkillsInAndDaysAvailable(skills, dayOfWeek);
        for (Employee e : results) {
            System.out.println("Name: " + e.getName());
            System.out.println("Skill: " + e.getSkills());
            System.out.println("Days: " + e.getDaysAvailable());
        }


        return "Critter Starter installed successfully";
    }

    private static CustomerDTO createCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("TestEmployee");
        customerDTO.setPhoneNumber("123-456-789");
        return customerDTO;
    }

    private static PetDTO createPetDTO() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("TestPet");
        petDTO.setType(PetType.CAT);
        return petDTO;
    }


    private Pet toPet(PetDTO dto) {
        Pet pet = new Pet();
        pet.setType(dto.getType());
        pet.setName(dto.getName());

        return pet;
    }
}
