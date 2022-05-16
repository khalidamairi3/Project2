package com.project2.telemedicineapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repo.AppointmentRepository;
import com.project2.telemedicineapi.repo.PatientRepository;

@Service
public class AppointmentService {

	// Dependency Injection for AppointmentRepository
	@Autowired
	AppointmentRepository appointmentRepository;
	
	// Method for creating a New Appointment
	public void createAppointment(Appointment newAppointment) {
		appointmentRepository.save(newAppointment);
	}

	// Method for Deleting an Appointment
	public void deleteAppointment(Appointment previousAppointment){
		appointmentRepository.delete(previousAppointment);
	}

	// Method for Fetching an Appointment By id
	public Appointment getAppointment(int id)
	{
		Appointment test1 = appointmentRepository.findById(id).get();
		return test1;
	}
}
	
	
	
	
	

