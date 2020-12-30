package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PetService {
    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public PetDTO savePet(PetDTO petDTO) {
        Pet saved = petRepository.save(toPet(petDTO));
        return toPetDTO(saved);
    }

    public PetDTO getPet(long petId) throws Throwable {
        Optional<Pet> optional = petRepository.findById(petId);
        Pet pet = optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(petId));
        return toPetDTO(pet);
    }

    private Pet toPet(PetDTO dto) {
        Pet pet = new Pet();
        pet.setType(dto.getType());
        pet.setName(dto.getName());
        pet.setCustomerId(dto.getOwnerId());

        return pet;
    }

    private PetDTO toPetDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setType(pet.getType());
        dto.setOwnerId(pet.getCustomerId());

        // TODO: add other fields

        if (pet.getOwner() != null) {
            dto.setOwnerId(pet.getOwner().getId());
        }

        return dto;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<Pet> pets = petRepository.findPetsByCustomerId(ownerId);
        return toPetsDTOList(pets);
    }

    private List<PetDTO> toPetsDTOList(List<Pet> pets) {
        List<PetDTO> dtos = new ArrayList<>();
        for (Pet pet : pets) {
            dtos.add(toPetDTO(pet));
        }

        return dtos;
    }
}
