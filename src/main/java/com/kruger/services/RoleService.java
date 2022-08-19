package com.kruger.services;

import com.kruger.models.Role;

public interface RoleService {
	
	public Role findByNameRole(String name);
	
	public Role saveRole(Role role);

}
