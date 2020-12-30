package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.data.Schedule;
import com.udacity.jdnd.course3.critter.persistance.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.PetRepository;
import com.udacity.jdnd.course3.critter.persistance.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.save(toSchedule(scheduleDTO));

        // get employees with ids
        List<Employee> employees = employeeRepository.findEmployeesByIdIn(scheduleDTO.getEmployeeIds());

        // get pets with ids
        List<Pet> pets = petRepository.findPetsByIdIn(scheduleDTO.getPetIds());

        // add them to Schedule instance
        schedule.setEmployees(employees);
        schedule.setPets(pets);

        // update the schedule
        Schedule updated = scheduleRepository.save(schedule);

        return toScheduleDTO(updated);
    }

    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(this::toScheduleDTO).collect(Collectors.toList());
    }

    private ScheduleDTO toScheduleDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setDate(schedule.getDate());
        dto.setActivities(schedule.getSkills());
        dto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        dto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));

        return dto;
    }

    private Schedule toSchedule(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setDate(dto.getDate());
        schedule.setSkills(dto.getActivities());

        return schedule;
    }
}
