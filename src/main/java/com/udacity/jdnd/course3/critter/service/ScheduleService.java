package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.data.Schedule;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // get employees by ids
        List<Employee> employees = employeeRepository.findEmployeesByIdIn(employeeIds);

        // get pets by ids
        List<Pet> pets = petRepository.findPetsByIdIn(petIds);

        // add them to Schedule instance
        savedSchedule.setEmployees(employees);
        savedSchedule.setPets(pets);

        // update the schedule
        return scheduleRepository.save(savedSchedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findScheduleByEmployeesId(employeeId);
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return scheduleRepository.findScheduleByPetsId(petId);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Pet> pets = petRepository.findPetsByOwnerId(customerId);

        return scheduleRepository.findAllByPetsIn(pets);
    }
}
