package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.entities.Patient;

import com.project2.telemedicineapi.repo.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class PatientService {
	
	// Dependency injection for PatientRepository
	@Autowired
	PatientRepository patientRepository;
	
// Method for Creating a new Patient	
	public void createPatient(Patient incomingPatient) {
		patientRepository.save(incomingPatient);
	}

// Method for deleting a Patient
	public void deletePatient(Patient incomingPatient){
		patientRepository.delete(incomingPatient);
	}

// Method for fetching a Patient by Id 
	public Patient getPatient(int id)
	{
		Patient test = patientRepository.findById(id).get();
		return test;
	}


	
	

	

	
	
	
	
}