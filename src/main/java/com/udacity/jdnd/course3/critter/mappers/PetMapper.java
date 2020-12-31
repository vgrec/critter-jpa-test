package com.udacity.jdnd.course3.critter.mappers;

import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PetMapper implements Mapper<PetDTO, Pet> {
    @Override
    public PetDTO toDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        BeanUtils.copyProperties(pet, dto);

        if (pet.getCustomer() != null) {
            dto.setOwnerId(pet.getCustomer().getId());
        }

        return dto;
    }

    @Override
    public Pet toEntity(PetDTO dto) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(dto, pet);
        return pet;
    }
}
