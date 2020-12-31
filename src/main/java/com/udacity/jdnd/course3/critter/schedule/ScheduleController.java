package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.mappers.ScheduleMapper;
import com.udacity.jdnd.course3.critter.persistance.data.Schedule;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleMapper mapper;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = mapper.toEntity(scheduleDTO);
        Schedule savedSchedule = scheduleService.createSchedule(
                schedule,
                scheduleDTO.getEmployeeIds(),
                scheduleDTO.getPetIds()
        );
        return mapper.toDTO(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return mapper.toListOfScheduleDTOs(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return mapper.toListOfScheduleDTOs(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return mapper.toListOfScheduleDTOs(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return mapper.toListOfScheduleDTOs(scheduleService.getScheduleForCustomer(customerId));
    }
}
