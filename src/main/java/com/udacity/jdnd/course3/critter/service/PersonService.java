package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Customer;
import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.OwnerRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Transactional
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
        Customer customer = toOwner(customerDTO);
        Customer saved = ownerRepository.save(customer);
        return toCustomerDTO(saved);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> owners = ownerRepository.findAll();

        for (Customer customer : owners) {
            List<Pet> ownersPets = petRepository.findPetsByCustomerId(customer.getId());
            customer.setPets(ownersPets);
        }

        List<CustomerDTO> customers = new ArrayList<>();
        for (Customer customer : owners) {
            customers.add(toCustomerDTO(customer));
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
        dto.setDaysAvailable(saved.getDaysAvailable());
        return dto;
    }

    public EmployeeDTO getEmployee(long employeeId) throws Throwable {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(employeeId));
        return toEmployeeDTO(employee);
    }

    public CustomerDTO getOwnerByPet(long petId) throws Throwable {
        Optional<Pet> optional = petRepository.findById(petId);
        Pet pet = optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(petId));

        Customer customer = pet.getCustomer();
        customer.setPets(petRepository.findPetsByCustomerId(customer.getId()));

        return toCustomerDTO(customer);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) throws Throwable {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(employeeId));
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    private Employee toEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setSkills(dto.getSkills());
        employee.setDaysAvailable(dto.getDaysAvailable());

        return employee;

    }

    private Customer toOwner(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setNotes(dto.getNotes());
        return customer;
    }

    private CustomerDTO toCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setNotes(customer.getNotes());

        if (customer.getPets() != null) {
            List<PetDTO> ownerPets = new ArrayList<>();
            for (Pet pet : customer.getPets()) {
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

        if (pet.getCustomer() != null) {
            dto.setOwnerId(pet.getCustomer().getId());
        }

        return dto;
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();
        DayOfWeek dayOfWeek = employeeRequestDTO.getDate().getDayOfWeek();

        List<Employee> employees = employeeRepository.findEmployeesBySkillsInAndDaysAvailable(skills, dayOfWeek);

        List<EmployeeDTO> resultList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSkills().containsAll(skills)) {
                resultList.add(toEmployeeDTO(employee));
            }
        }

        return resultList;
    }
}
