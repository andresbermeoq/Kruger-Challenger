package com.kruger.dto;

import java.time.LocalDate;


public interface EmployeDto {
	
	String getNombre();
	String getApellido();
	String getDireccion();
	String getCedula();
	String getTipoVacuna();
	Integer getDosis();
	LocalDate getFechaVacunacion();

}
