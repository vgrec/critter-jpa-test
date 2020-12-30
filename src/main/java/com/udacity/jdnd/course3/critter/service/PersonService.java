package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.data.Owner;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private EmployeeRepository employeeRepository;
    private OwnerRepository ownerRepository;
    private PetRepository petRepository;

    public PersonService(EmployeeRepository employeeRepository, OwnerRepository ownerRepository, PetRepository petRepository) {
        this.employeeRepository = employeeRepository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Owner owner = toOwner(customerDTO);
        Owner saved = ownerRepository.save(owner);
        return toCustomerDTO(saved);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Owner> owners = ownerRepository.findAll();

        for (Owner owner : owners) {
            List<Pet> ownersPets = petRepository.findPetsByCustomerId(owner.getId());
            owner.setPets(ownersPets);
        }

        List<CustomerDTO> customers = new ArrayList<>();
        for (Owner owner : owners) {
            customers.add(toCustomerDTO(owner));
        }
        return customers;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee saved = employeeRepository.save(toEmployee(employeeDTO));
        return toEmployeeDTO(saved);
    }

    private EmployeeDTO toEmployeeDTO(Employee saved) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(saved.getId());
        dto.setName(saved.getName());
        dto.setSkills(saved.getSkills());
        return dto;
    }

    public EmployeeDTO getEmployee(long employeeId) throws Throwable {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow(PersonNotFoundException::new);
        return toEmployeeDTO(employee);
    }

    private Employee toEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSkills(dto.getSkills());
        employee.setDaysAvailable(dto.getDaysAvailable());

        return employee;

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

        if (owner.getPets() != null) {
            List<PetDTO> ownerPets = new ArrayList<>();
            for (Pet pet : owner.getPets()) {
                ownerPets.add(toPetDTO(pet));
            }
            List<Long> petIds = ownerPets.stream().map(PetDTO::getId).collect(Collectors.toList());
            dto.setPetIds(petIds);
        }

        return dto;
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


    private Pet toPet(PetDTO dto) {
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setType(dto.getType());
        return pet;
    }

    class PersonNotFoundException extends Exception {
        PersonNotFoundException() {
            super("Person not found");
        }
    }
}
