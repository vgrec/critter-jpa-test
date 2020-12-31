package com.udacity.jdnd.course3.critter.mappers;

import com.udacity.jdnd.course3.critter.persistance.data.Customer;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper implements Mapper<CustomerDTO, Customer> {

    @Autowired
    private PetMapper petMapper;

    @Override
    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);

        // If customer has pets, then set the pet ids to the dto
        if (customer.getPets() != null) {
            List<Long> petIds = customer.getPets()
                    .stream()
                    .map(Pet::getId)
                    .collect(Collectors.toList());
            dto.setPetIds(petIds);
        }

        return dto;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    @Override
    public List<CustomerDTO> toListOfDTOs(List<Customer> customers) {
        return customers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
