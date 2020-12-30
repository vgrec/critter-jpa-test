package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.service.PersonService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;

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

    @GetMapping("/test")
    public String test() throws Throwable {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Grigorash");

        personService.saveEmployee(dto);

        EmployeeDTO saved = personService.getEmployee(1);
        System.out.println("Emp id: " + saved.getId());
        System.out.println("Emp days: " + saved.getDaysAvailable());

        saved.setName("Updating...");
        saved.setDaysAvailable(Sets.newHashSet(DayOfWeek.WEDNESDAY, DayOfWeek.TUESDAY));
        EmployeeDTO updated = personService.saveEmployee(saved);

        System.out.println("Upd id: " + updated.getId());
        System.out.println("Upd days: " + updated.getDaysAvailable());


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
