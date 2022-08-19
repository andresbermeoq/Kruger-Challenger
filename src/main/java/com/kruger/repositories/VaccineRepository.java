package com.kruger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.models.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

}
