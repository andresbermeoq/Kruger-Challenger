package com.kruger.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	Optional<Employee> findByDni(String dni);
}
