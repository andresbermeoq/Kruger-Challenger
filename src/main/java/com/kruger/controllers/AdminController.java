package com.kruger.controllers;

import static com.kruger.utils.Constants.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/admin")
@Api(tags = ADMIN_TAG_NOMBRE, description = ADMIN_TAG_DESCRIPCION)
public class AdminController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ApiOperation(value = ADMIN_LIST_STATUS)
	@GetMapping(value = "/estado")
	public ResponseEntity<?> listByStatus(@RequestBody Map<String, String> statusVaccine) {
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByStatus(statusVaccine.get("estado")));
		
	}
	
	@ApiOperation(value = ADMIN_LIST_TYPE)
	@GetMapping(value = "/tipo")
	public ResponseEntity<?> listByType(@RequestBody Map<String, String> typeVaccine) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByTypeVaccine(typeVaccine.get("tipo")));		
	}
	
	@ApiOperation(value = ADMIN_LIST_RANGE)
	@GetMapping(value = "/rango")
	public ResponseEntity<?> listByRange(@RequestBody Map<String, String> datesVaccine) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate iniciaLocalDate = LocalDate.parse(datesVaccine.get("fechaInicio"), dateTimeFormatter);
		LocalDate finalLocalDate = LocalDate.parse(datesVaccine.get("fechaFin"), dateTimeFormatter);
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.findByRangeDate(iniciaLocalDate, finalLocalDate));
	}

}
