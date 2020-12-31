package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Customer;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Pet savePet(Pet pet, Long ownerId) {
        Pet savedPet = petRepository.save(pet);
        Optional<Customer> optionalOwner = ownerRepository.findById(ownerId);

        if (optionalOwner.isPresent()) {
            Customer customer = optionalOwner.get();
            savedPet.setCustomer(customer);
            return petRepository.save(savedPet);
        } else {
            return savedPet;
        }
    }

    public Pet getPet(long petId) throws Throwable {
        Optional<Pet> optional = petRepository.findById(petId);
        return optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(petId));
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return petRepository.findPetsByCustomerId(ownerId);
    }
}
