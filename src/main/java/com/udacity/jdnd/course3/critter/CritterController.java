package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.service.PersonService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        CustomerDTO customerDTO = createCustomerDTO();
        CustomerDTO newCustomer = personService.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getId());
        PetDTO newPet = petService.savePet(petDTO);

        //make sure pet contains customer id
        PetDTO retrievedPet = petService.getPet(newPet.getId());
        Assertions.assertEquals(retrievedPet.getId(), newPet.getId());
        Assertions.assertEquals(retrievedPet.getOwnerId(), newCustomer.getId());

        //make sure you can retrieve pets by owner
        List<PetDTO> pets = petService.getPetsByOwner(newCustomer.getId());

        for (PetDTO dto : pets) {
            System.out.println(dto.getName());
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
