package com.kruger.models;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name="Kruger_Employee", indexes={@Index(name="Kruger_Employee_employee_dni_IX", columnList="employee_dni", unique=true)})
@Accessors(chain = true)
@Getter
@Setter
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user", unique=true, nullable=false, precision=19)
    private Long id;
	
    @Column(name="employee_name", nullable=false, length=100)
    @NotEmpty(message = "El nombre es requerido")
    private String name;
    
    @Column(name="employee_last_name", nullable=false, length=100)
    @NotEmpty(message = "El apellido es requerido")
    private String lastName;
    
    @Column(name="employee_email", nullable=false, length=100)
    @NotEmpty(message = "El email es requerido")
    private String email;
    
    @Column(name="employee_dni", unique=true, nullable=false, length=10)
    @NotEmpty(message = "La cedula es requerido")
    private String dni;
    
    @Column(name="employee_date_birthday")
    private OffsetDateTime dateBirthday;
    
    @Column(name="employee_mobile", length=10)
    private String mobile;
    @Column(name="employee_status_vaccine", length=15)
    private String statusVaccine;
    
    @OneToMany(mappedBy="employee")
    private Set<Vaccine> vaccines;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_user")
    private User user;
    
    @PrePersist
    public void prePersist() {
    	this.dateBirthday = OffsetDateTime.now();
    	this.mobile = "";
    	this.statusVaccine = "No Vacunado";
    }

	public Employee(String name, String lastName, String email, String dni) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.dni = dni;
	}
	
	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email + ", dni=" + dni
				+ ", dateBirthday=" + dateBirthday + ", mobile=" + mobile + ", statusVaccine=" + statusVaccine
				+ ", vaccines=" + vaccines + ", user=" + user + "]";
	}

    
    

}
