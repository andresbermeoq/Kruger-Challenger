package com.kruger.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.models.Role;
import com.kruger.repositories.RoleRepository;
import com.kruger.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByNameRole(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.saveAndFlush(role);
	}

}
