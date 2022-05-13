package com.project2.telemedicineapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repo.PatientRepository;
import com.project2.telemedicineapi.services.PatientService;


@RestController
@RequestMapping("/patient")
public class PatientController {
	
@Autowired
PatientRepository patientRepository;

@Autowired
PatientService patientService;

// Register a new Patient
@PostMapping("/register")
public void createPatient(@RequestBody Patient incomingPatient) {
	patientService.createPatient(incomingPatient);
}

//Get a list of all Patients
@GetMapping("/patientlist")
public List<Patient> viewAllPatients() {
	return patientRepository.findAll();
}

//Get Patient By Id
@GetMapping("/patientbyid/{id}")
public Optional<Patient> findPatientById(@PathVariable int id) {
	return patientRepository.findById(id);
}

//Get Patient By phoneNum
@GetMapping("/patientbyphoneNum/{phoneNum}")
public Patient findPatientByphoneNum(@PathVariable String phoneNum) {
	return patientRepository.getPatientByphoneNum(phoneNum);
}

// Edit Patient Profile
//Change status of an Appointment
		@PostMapping("/editpatientprofile")
		public void editPatientProfile(@RequestBody Patient existingPatient) {
			patientRepository.save(existingPatient);
		}	
				}	

