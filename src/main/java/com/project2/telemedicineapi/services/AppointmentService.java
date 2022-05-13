package com.project2.telemedicineapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repo.AppointmentRepository;
import com.project2.telemedicineapi.repo.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	public void createAppointment(Appointment newAppointment) {
		appointmentRepository.save(newAppointment);
	}

	public void deleteAppointment(Appointment previousAppointment){
		appointmentRepository.delete(previousAppointment);
	}

	public Appointment getAppointment(int id)
	{
		Appointment test1 = appointmentRepository.findById(id).get();
		return test1;
	}
}
	
	
	
	
	

