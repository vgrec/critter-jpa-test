package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Customer;
import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

@Service
@Transactional
public class PersonService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
            List<Pet> customerPets = petRepository.findPetsByCustomerId(customer.getId());
            customer.setPets(customerPets);
        }

        return customers;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(long employeeId) throws Throwable {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        return optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(employeeId));
    }

    public Customer getOwnerByPet(long petId) throws Throwable {
        Optional<Pet> optional = petRepository.findById(petId);
        Pet pet = optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(petId));

        Customer customer = pet.getCustomer();
        customer.setPets(petRepository.findPetsByCustomerId(customer.getId()));

        return customer;
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) throws Throwable {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow((Supplier<Throwable>) () -> new ItemNotFoundException(employeeId));
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills,  DayOfWeek dayOfWeek) {
        List<Employee> employees = employeeRepository.findEmployeesBySkillsInAndDaysAvailable(skills, dayOfWeek);

        List<Employee> resultList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSkills().containsAll(skills)) {
                resultList.add(employee);
            }
        }

        return resultList;
    }
}
