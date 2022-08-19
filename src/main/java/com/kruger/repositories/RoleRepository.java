package com.kruger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
