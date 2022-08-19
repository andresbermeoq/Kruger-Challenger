package com.kruger.models;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name="Krugger_Vaccine")
@Accessors(chain = true)
@Getter
@Setter
public class Vaccine implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="vaccine_id", unique=true, nullable=false, precision=19)
    private Long id;
	
    @Column(name="vaccine_type", nullable=false, length=100)
    private String vaccineType;
    
    @Column(name="vaccine_date", nullable=false)
    private OffsetDateTime vaccineDate;
    
    @Column(name="vaccine_number", nullable=false, precision=10)
    private int vaccineNumber;
    
    @ManyToOne(optional=false)
    @JoinColumn(name="vaccine_user", nullable=false)
    private Employee employee;

    
}
