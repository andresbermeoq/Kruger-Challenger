package com.kruger.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.kruger.models.Employee;
import com.kruger.models.Vaccine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class CreateEmployeeDto {
	
	@NotEmpty(message = "El nombre es requerido.")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo debe contener letras")
	private String nombre;
	
	@NotEmpty(message = "El apellido es requerido.")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El apellido solo debe contener letras")
	private String apellido;
	
	
	@Email(message = "No es un correo valido.", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	@NotEmpty(message = "El apellido es requerido.")
	private String email;
	
	
	@NotEmpty(message = "La cedula es requerida.")
	@Size(min = 10, max = 10, message = "El dni debe tener 10 digitos")
	private String cedula;
	
	
	private LocalDate fechaNacimiento;
	private String direccion;
	private String telefonoCelular;
	private String estadoVacunas;
	
	private List<Vaccine> vacunas = new ArrayList<>();
	
	public Employee ToEmployee() {
		return new Employee().setName(nombre).setLastName(apellido).setEmail(email).setDni(cedula)
							 .setDateBirthday(fechaNacimiento).setAddress(direccion).setMobile(telefonoCelular)
							 .setStatusVaccine(estadoVacunas).setVaccines(vacunas);
	}
	

	@Override
	public String toString() {
		return "CreateEmployeeDto [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", cedula="
				+ cedula + ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + ", telefonoCelular="
				+ telefonoCelular + ", estadoVacunas=" + estadoVacunas + ", vacunas=" + vacunas + "]";
	}
	

}
