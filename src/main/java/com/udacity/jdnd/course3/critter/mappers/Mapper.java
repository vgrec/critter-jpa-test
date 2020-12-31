package com.udacity.jdnd.course3.critter.mappers;

import com.udacity.jdnd.course3.critter.persistance.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

public interface Mapper<DTO, Entity> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto);
}