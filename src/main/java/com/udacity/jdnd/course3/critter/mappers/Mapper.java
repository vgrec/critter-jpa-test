package com.udacity.jdnd.course3.critter.mappers;

import java.util.Collections;
import java.util.List;

public interface Mapper<DTO, Entity> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto);

    default List<DTO> toListOfDTOs(List<Entity> entities) {
        // override if needed
        return Collections.emptyList();
    }

    default List<Entity> toListOfEntities(List<DTO> dtos) {
        // override if needed
        return Collections.emptyList();
    }
}