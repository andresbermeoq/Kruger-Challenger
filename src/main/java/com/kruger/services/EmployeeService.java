package com.kruger.services;

import java.time.LocalDate;
import java.util.List;

import com.kruger.dto.EmployeDto;
import com.kruger.models.Employee;

public interface EmployeeService extends AbstractService<Employee, Long> {
	
	public List<Employee> findByStatus(String status);
	public List<EmployeDto> findByTypeVaccine(String status);
	public List<EmployeDto> findByRangeDate(LocalDate dateInicial, LocalDate dateFinal);

}
