package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Owner;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    public List<PetDTO> getPetsByOwner(long ownerId) throws Throwable {
        Optional<Owner> optional = ownerRepository.findById(ownerId);
        Owner owner = optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(ownerId));
        List<Pet> pets = owner.getPets();

        return toPetsDTOList(pets);
    }

    private List<PetDTO> toPetsDTOList(List<Pet> pets) {
        List<PetDTO> dtos = new ArrayList<>();
        for (Pet pet : pets) {
            dtos.add(toPetDTO(pet));
        }

        return dtos;
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
}
