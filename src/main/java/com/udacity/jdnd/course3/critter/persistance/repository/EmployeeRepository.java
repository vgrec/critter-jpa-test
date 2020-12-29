package com.udacity.jdnd.course3.critter.persistance.repository;

import com.udacity.jdnd.course3.critter.persistance.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
