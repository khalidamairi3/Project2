package com.project2.telemedicineapi.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repo.AppointmentRepository;
import com.project2.telemedicineapi.services.AppointmentService;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	// Initialising Logger Class for logging Functionality
	Logger logger= LoggerFactory.getLogger(PatientController.class);		
	
	
// Dependency Injection for AppointmentRepository	
	@Autowired
	AppointmentRepository appointmentRepository;

// Dependency injection for Appointment Service	
	@Autowired
	AppointmentService appointmentService;

// Book a new appointment
	@PostMapping("/booknewappointment")
	public void createAppointment(@RequestBody Appointment newAppointment) {
		logger.info("About to book a new appointment.");
		appointmentService.createAppointment(newAppointment);
	}

//Get a list of all Appointments
	@GetMapping("/allappointments")
	public List<Appointment> viewAllAppointments() {
		logger.info("Method for finding all Appointments is accessed");
		return appointmentRepository.findAll();
	}

//Get Appointment By Id
	@GetMapping("/getbyid/{id}")
	public Optional<Appointment> findAppointmentById(@PathVariable int id) {
		logger.info("Fetching an appointment By Id");
		return appointmentRepository.findById(id);
	}
	
//Get All Appointments By Patient Name
	@GetMapping("/getbypatientname/{patient}")
	public List<Appointment> findAppointmentByPatient(@PathVariable String patient) {
		logger.info("Fetching an appointment By Patient's Name");
		return appointmentRepository.getAppointmentByPatient(patient);
	}
	
//Get All Appointments By Doctor Name
		@GetMapping("/getbydoctorname/{doctor}")
		public List<Appointment> findAppointmentByDoctor(@PathVariable String doctor) {
			logger.info("Fetching an appointment By Doctor's Name");
			return appointmentRepository.getAppointmentByDoctor(doctor);
		}
	
// Change status of an Appointment
		@PostMapping("/update/status")
		public void updateAppointment(@RequestBody Appointment processedAppointment) {
			logger.info("Accessing method for Changing Status of appointment");
			appointmentRepository.save(processedAppointment);
		}	
		   }
	
	
	

