package com.udacity.jdnd.course3.critter.mappers;

import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import com.udacity.jdnd.course3.critter.persistance.data.Pet;
import com.udacity.jdnd.course3.critter.persistance.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper implements Mapper<ScheduleDTO, Schedule> {

    @Override
    public ScheduleDTO toDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();

        BeanUtils.copyProperties(schedule, dto);

        // Copy the rest of the properties that don't have a property match
        dto.setActivities(schedule.getSkills());
        dto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        dto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));

        return dto;
    }

    @Override
    public Schedule toEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(dto, schedule);

        // Copy the rest of the properties that don't have a property match
        schedule.setSkills(dto.getActivities());

        return schedule;
    }

    @Override
    public List<ScheduleDTO> toListOfDTOs(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}