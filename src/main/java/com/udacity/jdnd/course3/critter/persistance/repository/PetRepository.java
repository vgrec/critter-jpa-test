package com.udacity.jdnd.course3.critter.persistance.repository;

import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findPetsByCustomerId(Long customerId);

    List<Pet> findPetsByIdIn(List<Long> petIds);

}
