package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Owner;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private EmployeeRepository employeeRepository;
    private OwnerRepository ownerRepository;

    public PersonService(EmployeeRepository employeeRepository, OwnerRepository ownerRepository) {
        this.employeeRepository = employeeRepository;
        this.ownerRepository = ownerRepository;
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Owner owner = toOwner(customerDTO);
        Owner saved = ownerRepository.save(owner);
        return toCustomerDTO(saved);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Owner> owners = ownerRepository.findAll();
        List<CustomerDTO> customers = new ArrayList<>();
        for (Owner owner : owners) {
            customers.add(toCustomerDTO(owner));
        }
        return customers;
    }

    private Owner toOwner(CustomerDTO dto) {
        Owner owner = new Owner();
        owner.setName(dto.getName());
        owner.setPhoneNumber(dto.getPhoneNumber());
        owner.setNotes(dto.getNotes());
        return owner;
    }

    private CustomerDTO toCustomerDTO(Owner owner) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(owner.getId());
        dto.setName(owner.getName());
        dto.setPhoneNumber(owner.getPhoneNumber());
        dto.setNotes(owner.getNotes());

        return dto;
    }

    private Pet toPet(PetDTO dto) {
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setType(dto.getType());
        return pet;
    }
}
