package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.entities.Patient;

import com.project2.telemedicineapi.repo.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	public void createPatient(Patient incomingPatient) {
		patientRepository.save(incomingPatient);
	}

	public void deleteReimbursement(Patient incomingPatient){
		patientRepository.delete(incomingPatient);
	}

	public Patient getPatient(int id)
	{
		Patient test = patientRepository.findById(id).get();
		return test;
	}

	
	

	

	
	
	
	
}