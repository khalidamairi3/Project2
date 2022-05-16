package com.project2.telemedicineapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
// Initialising Logger Class for logging Functionality
Logger logger= LoggerFactory.getLogger(PatientController.class);	

// Dependency Injection for PatientRepository	
@Autowired
PatientRepository patientRepository;

//Dependency Injection for PatientService
@Autowired
PatientService patientService;

// Register a new Patient
@PostMapping("/register")
public void createPatient(@RequestBody Patient incomingPatient) {
	// For logging message, when this method is used
	logger.info("About to start CreatePatient Method");
	patientService.createPatient(incomingPatient);
}

//Get a list of all Patients
@GetMapping("/patientlist")
public List<Patient> viewAllPatients() {
	logger.info("About to Access all Patients List");
	return patientRepository.findAll();
}

//Get Patient By Id
@GetMapping("/patientbyid/{id}")
public Optional<Patient> findPatientById(@PathVariable int id) {
	logger.info("Fetching a Patient by Id");
	return patientRepository.findById(id);
}

//Get Patient By phoneNum
@GetMapping("/patientbyphoneNum/{phoneNum}")
public Patient findPatientByphoneNum(@PathVariable String phoneNum) {
	logger.info("Fetching a Patient by Phone Number");
	return patientRepository.getPatientByphoneNum(phoneNum);
}

// Edit Patient Profile
//Change status of an Appointment
		@PostMapping("/editpatientprofile")
		public void editPatientProfile(@RequestBody Patient existingPatient) {
			logger.info("Editing the Patient Profile");
			patientRepository.save(existingPatient);
		}	
				

// Delete patient by phone Number
@DeleteMapping("/deletepatientbyId/{id}")
public void DeletingPatientByphoneNum(@PathVariable int id) {
	logger.info("Accessing Method for Deleting Patient");
	patientRepository.deleteById(id);
}
		}	