package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.mappers.PetMapper;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetMapper petMapper;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petMapper.toEntity(petDTO);
        Pet savedPet = petService.savePet(pet, petDTO.getOwnerId());
        return petMapper.toDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) throws Throwable {
        return petMapper.toDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petMapper.toListOfDTOs(petService.getAllPets());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        return petMapper.toListOfDTOs(pets);
    }
}
