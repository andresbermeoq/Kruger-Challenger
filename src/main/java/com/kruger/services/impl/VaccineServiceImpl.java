package com.kruger.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.models.Vaccine;
import com.kruger.repositories.VaccineRepository;
import com.kruger.services.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService {
	
	@Autowired
	private VaccineRepository vaccineRepository;

	@Override
	public List<Vaccine> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vaccine saveEntity(Vaccine entity) {
		return vaccineRepository.save(entity);
	}

	@Override
	public Vaccine findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vaccine updateEntity(Long id, Vaccine entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEntity(Long id) {
		// TODO Auto-generated method stub
		
	}

}
