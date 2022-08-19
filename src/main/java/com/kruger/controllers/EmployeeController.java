package com.kruger.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.dto.CreateEmployeeDto;
import com.kruger.dto.MessageDto;
import com.kruger.models.Employee;
import com.kruger.services.EmployeeService;


@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	private ResponseEntity<?> validated(BindingResult bindingResult) {
		Map<String, String> errors = new HashMap<>();
		
		bindingResult.getFieldErrors().forEach(error -> {
			errors.put(error.getField(), "EL CAMPO " + error.getField() + " DEBE " + error.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEmployee(@Valid @RequestBody CreateEmployeeDto employeeDto, BindingResult result){
		if (result.hasErrors()) {
			return validated(result);
		}
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEntity(employeeDto.ToEmployee()));			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageDto("LA CEDULA YA EXISTE"));
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listAllEmployees() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
	}



}
