package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.persistance.data.Owner;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public String test() throws Throwable {
        Owner owner = new Owner();
        owner.setName("Mozart de la Bulboaca");
        owner.setNotes("Take care of it");
        owner.setPhoneNumber("+498204457844");

        ownerRepository.save(owner);

        Pet pet = new Pet();
        pet.setName("Cociuleanu");
        pet.setType(PetType.DOG);
        pet.setOwner(owner);

        petRepository.save(pet);

        Optional<Pet> o = petRepository.findById(2L);
        if (o.isPresent()) {
            Pet p = o.get();
            System.out.println("id: " + p.getId());
            System.out.println("name: " + p.getName());
            System.out.println("owner: " + p.getOwner().getName() + " - " + p.getOwner().getPhoneNumber());
            System.out.println("type: " + p.getType());
        } else {
            System.out.println("Could not get");
        }


        return "Critter Starter installed successfully";
    }
}
