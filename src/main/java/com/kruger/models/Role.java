package com.kruger.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity(name="Kruger_Role")
@Accessors(chain = true)
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_role", unique=true, nullable=false, precision=19)
    private Long id;
	
    @Column(name="role_name")
    private String name;
    @Column(name="role_description")
    private String description;
    
	public Role(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Role() {
		super();
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
