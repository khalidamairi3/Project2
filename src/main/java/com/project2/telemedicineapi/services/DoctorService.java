package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.repo.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public void createDoctor(Doctor clinicDoctor) {
		doctorRepository.save(clinicDoctor);
	}
}