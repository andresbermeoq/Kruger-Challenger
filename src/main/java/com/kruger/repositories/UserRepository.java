package com.kruger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
