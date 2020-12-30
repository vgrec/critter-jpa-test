package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Owner;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public PetDTO savePet(PetDTO petDTO) {
        Pet savedPet = petRepository.save(toPet(petDTO));
        Optional<Owner> optionalOwner = ownerRepository.findById(petDTO.getOwnerId());
        if (optionalOwner.isPresent()) {
            Owner owner = optionalOwner.get();
            savedPet.setOwner(owner);
            return toPetDTO(petRepository.save(savedPet));
        } else {
            return toPetDTO(savedPet);
        }
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

        return pet;
    }

    private PetDTO toPetDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setType(pet.getType());

        if (pet.getOwner() != null) {
            dto.setOwnerId(pet.getOwner().getId());
        }

        return dto;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<Pet> pets = petRepository.findPetsByOwnerId(ownerId);
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
