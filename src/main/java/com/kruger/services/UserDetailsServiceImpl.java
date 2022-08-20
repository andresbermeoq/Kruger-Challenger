package com.kruger.services;

import org.springframework.stereotype.Service;

import com.kruger.models.User;
import com.kruger.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			logger.error("Error en el login: No existe el usuario" + username);
			throw new UsernameNotFoundException("Error en el login: No existe el usuario" + username);
		}
		
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
																 .password(user.getPassword())
																 .roles(user.getRole().getName().toUpperCase())
																 .build();
	}

}
