package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.mappers.CustomerMapper;
import com.udacity.jdnd.course3.critter.mappers.PetMapper;
import com.udacity.jdnd.course3.critter.persistance.data.Customer;
import com.udacity.jdnd.course3.critter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PersonService personService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = personService.saveCustomer(customer);
        return customerMapper.toDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.toListOfDTOs(personService.getAllCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) throws Throwable {
        Customer savedCustomer = personService.getOwnerByPet(petId);
        return customerMapper.toDTO(savedCustomer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return personService.saveEmployee(employeeDTO);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) throws Throwable {
        return personService.getEmployee(employeeId);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws Throwable {
        personService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return personService.findEmployeesForService(employeeRequestDTO);
    }

}
