package com.kruger.controllers;

import static com.kruger.utils.Constants.*;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.dto.CreateEmployeeDto;
import com.kruger.dto.MessageDto;
import com.kruger.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("api/v1/employee")
@Api(tags = EMPLOYEE_TAG_NOMBRE, description = EMPLOYEE_TAG_DESCRIPCION)
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
	
	@ApiOperation(value = EMPLOYEE_CREATE, response = CreateEmployeeDto.class)
	@PostMapping
	public ResponseEntity<?> createEmployee(@Valid @RequestBody CreateEmployeeDto employeeDto, BindingResult result){
		if (result.hasErrors()) {
			return validated(result);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEntity(employeeDto.ToEmployee()));
	}
	
	@ApiOperation(value = EMPLOYEE_LIST)
	@GetMapping
	public ResponseEntity<?> listAllEmployees() {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll());
	}
	
	@ApiOperation(value = EMPLOYEE_DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@Valid @PathVariable String id) {
		
		try {
			employeeService.deleteEntity(Long.parseLong(id));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageDto("BORRADO CORRECTAMENTE"));			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@ApiOperation(value = EMPLOYEE_UPDATE, response = CreateEmployeeDto.class)
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody CreateEmployeeDto employee, BindingResult result, @PathVariable String id)  {
		if (result.hasErrors()) {
			return validated(result);
		}
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEntity(Long.parseLong(id), employee.ToEmployee()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


}
