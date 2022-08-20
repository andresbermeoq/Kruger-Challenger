package com.kruger.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.dto.EmployeDto;
import com.kruger.models.Employee;
import com.kruger.models.User;
import com.kruger.models.Vaccine;
import com.kruger.repositories.EmployeeRepository;
import com.kruger.repositories.RoleRepository;
import com.kruger.repositories.UserRepository;
import com.kruger.services.EmployeeService;
import com.kruger.utils.NoFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final String EMPLOYEE_NOT_FOUND = "EMPLOYEE NOT FOUND";
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee saveEntity(Employee entity) {
		
		String username = entity.getName().charAt(0) + entity.getLastName();
		String password = "Password";
				
		entity.setUser(getEmployeeUser(username, password));
		
		return employeeRepository.saveAndFlush(entity);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Employee findById(Long id) {
		Optional<Employee> employeeActual = employeeRepository.findById(id);
		if (!employeeActual.isPresent()) {
			throw new NoFoundException(EMPLOYEE_NOT_FOUND);
		}
		return employeeActual.get();
	}

	@Override
	@Transactional
	public Employee updateEntity(Long id, Employee entity) {
		Employee actualEmployee = findById(id);
		
		actualEmployee.setDateBirthday(entity.getDateBirthday());
		actualEmployee.setMobile(entity.getMobile());
		actualEmployee.setAddress(entity.getAddress());
		actualEmployee.setStatusVaccine(entity.getStatusVaccine());
		
		if (actualEmployee.getStatusVaccine().equals("Vacunado")) {
			List<Vaccine> vacunas = new ArrayList<>(); 
			
			for (Vaccine vaccine: entity.getVaccines()) {
				vacunas.add(new Vaccine(vaccine.getVaccineType(), vaccine.getVaccineDate(), vaccine.getVaccineNumber(), actualEmployee));
			}
			
			actualEmployee.setVaccines(vacunas);
		}
			
		return employeeRepository.save(actualEmployee);
	}

	@Override
	@Transactional
	public void deleteEntity(Long id) {
		employeeRepository.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Employee> findByStatus(String status) {
		return employeeRepository.findByStatusVaccine(status);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EmployeDto> findByTypeVaccine(String status) {
		return employeeRepository.findByType(status);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeDto> findByRangeDate(LocalDate dateInicial, LocalDate dateFinal) {
		return employeeRepository.findByRange(dateInicial, dateFinal);
	}
	
	@Transactional(readOnly = true)
	private boolean existsUsername(String username) {
		if (userRepository.findByUsername(username) != null) {
			return true;
		}
		return false;
	}

	private User getEmployeeUser(String username, String password) {
		User user = new User();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(roleRepository.findByName("ROLE_USER"));
		
		return userRepository.save(user);
	}
	
}
