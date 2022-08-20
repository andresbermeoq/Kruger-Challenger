package com.kruger.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kruger.dto.EmployeDto;
import com.kruger.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByDni(String dni);

	List<Employee> findByStatusVaccine(String statusVaccine);

	@Query(value = "SELECT e.employee_name as nombre, e.employee_last_name as apellido, e.employee_address as direccion, e.employee_dni as cedula, "
			+ "v.vaccine_type as tipoVacuna, v.vaccine_number as dosis, v.vaccine_date as fechaVacunacion "
			+ "FROM kruger_employee e LEFT JOIN kruger_vaccine v on e.employee_id = v.vaccine_employee WHERE v.vaccine_type = ?1 "
			+ "group by v.vaccine_date, v.vaccine_type, v.vaccine_number, e.employee_name, e.employee_last_name, e.employee_address, e.employee_dni "
			+ "order by e.employee_last_name, e.employee_name", nativeQuery = true)
	List<EmployeDto> findByType(String name);

	@Query(value = "select e.employee_name as nombre, e.employee_last_name as apellido, e.employee_address as direccion, e.employee_dni as cedula, "
			+ "v.vaccine_type as tipoVacuna, v.vaccine_number as dosis, v.vaccine_date as fechaVacunacion "
			+ "from kruger_employee e left join kruger_vaccine v on e.employee_id = v.vaccine_employee where v.vaccine_date >= ?1 AND v.vaccine_date <= ?2 "
			+ "group by v.vaccine_date, v.vaccine_type, v.vaccine_number, e.employee_name, e.employee_last_name, e.employee_address, e.employee_dni "
			+ "order by e.employee_last_name, e.employee_name", nativeQuery = true)
	List<EmployeDto> findByRange(LocalDate dateInicial, LocalDate dateFinal);
}