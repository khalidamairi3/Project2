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
import com.project2.telemedicineapi.repo.AppointmentRepository;
import com.project2.telemedicineapi.services.AppointmentService;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	AppointmentService appointmentService;

	// Book a new appointment
	@PostMapping("/booknewappointment")
	public void createAppointment(@RequestBody Appointment newAppointment) {
		appointmentService.createAppointment(newAppointment);
	}

	//Get a list of all Appointments
	@GetMapping("/allappointments")
	public List<Appointment> viewAllPatients() {
		return appointmentRepository.findAll();
	}

	//Get Appointment By Id
	@GetMapping("/getbyid/{id}")
	public Optional<Appointment> findAppointmentById(@PathVariable int id) {
		return appointmentRepository.findById(id);
	}
	
	//Get All Appointments By Patient Name
	//Get Patient By phoneNum
	@GetMapping("/getbypatientname/{patient}")
	public List<Appointment> findAppointmentByPatient(@PathVariable String patient) {
		return appointmentRepository.getAppointmentByPatient(patient);
	}
	
	//Get All Appointments By Doctor Name
		@GetMapping("/getbydoctorname/{doctor}")
		public List<Appointment> findAppointmentByDoctor(@PathVariable String doctor) {
			return appointmentRepository.getAppointmentByDoctor(doctor);
		}
	
	// Change status of an Appointment
		@PostMapping("/update/status")
		public void updateReimbursement(@RequestBody Appointment processedAppointment) {
			appointmentRepository.save(processedAppointment);
		}	
		   }
	
	
	
//	//Get Patient By phoneNum
//	@GetMapping("/patientbyphoneNum/{phoneNum}")
//	public Patient findPatientByphoneNum(@PathVariable String phoneNum) {
//		return patientRepository.getPatientByphoneNum(phoneNum);
//	}



