package com.project2.telemedicineapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.repo.DoctorRepository;
import com.project2.telemedicineapi.services.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
@Autowired
DoctorRepository doctorRepository;

@Autowired
DoctorService doctorService;

// Register a new Patient
@PostMapping("/register")
public void createDoctor(@RequestBody Doctor clinicDoctor) {
	doctorService.createDoctor(clinicDoctor);
}

//Get a List of All Patients
//Get a list of all Patients
@GetMapping("/doctorlist")
public List<Doctor> viewAllDoctors() {
	return doctorRepository.findAll();
}



}