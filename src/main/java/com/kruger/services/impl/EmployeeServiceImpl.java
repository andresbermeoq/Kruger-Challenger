package com.kruger.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.models.Employee;
import com.kruger.models.User;
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
		
		User user = new User();
		
		String username = entity.getName().charAt(0) + entity.getLastName();
		user.setUsername(username);
		user.setPassword(username);
		user.setRole(roleRepository.findByName("ROLE_USER"));
		
		userRepository.save(user);
		
		
		entity.setUser(userRepository.findByUsername(username));
		
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
	public Employee updateEntity(Long id, Employee entity) {
		return saveEntity(entity);
	}

	@Override
	public Employee deleteEntity(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean existsUsername(String username) {
		if (userRepository.findByUsername(username) != null) {
			return true;
		}
		return false;
	}

	

}
