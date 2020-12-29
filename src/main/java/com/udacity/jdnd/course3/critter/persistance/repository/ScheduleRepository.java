package com.udacity.jdnd.course3.critter.persistance.repository;

import com.udacity.jdnd.course3.critter.persistance.data.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
