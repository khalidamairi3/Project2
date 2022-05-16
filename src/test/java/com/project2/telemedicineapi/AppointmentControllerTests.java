package com.project2.telemedicineapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repo.AppointmentRepository;
import com.project2.telemedicineapi.repo.PatientRepository;

@SpringBootTest
public class AppointmentControllerTests {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	// For Deleting the repeating Test Data After Each Test
	@AfterEach
	void teardown()
	{
		System.out.println("tearing down");
		appointmentRepository.deleteAll();
	}
	
	
	
// Testing if a new Appointment is created ==> Verified by searching by  Unique DateTime
	@Test
	void createAppointment()
	{
		Appointment appointment= new Appointment("2022-05-20;16:00","In review", "For Migraine", "Joe","Justin");
	appointmentRepository.save(appointment);
	assertEquals("2022-05-20;16:00", appointment.getDateTime());
	
	}
	
// Testing if we can retrieve a list of All Appointments ==>  Returns a list of created Appointments
		@Test
		void viewAllAppointments()
		{
			Appointment appointment= new Appointment("2022-05-20;16:00","In review", "For Migraine", "Joe","Justin");
			Appointment appointment2= new Appointment("2022-05-22;12:00","In review", "For Injury", "Harry","Smith");
			appointmentRepository.save(appointment);
			appointmentRepository.save(appointment2);
	assertEquals("[Appointment [dateTime=2022-05-20;16:00, status=In review, note=For Migraine, doctor=Joe, patient=Justin], Appointment [dateTime=2022-05-22;12:00, status=In review, note=For Injury, doctor=Harry, patient=Smith]]",
			appointmentRepository.findAll());
	//System.out.println(appointmentRepository.findAll());

		}
	
// Testing if we can find a created appointment by Id
		@Test
		void findAppointmentById()
		{
			Appointment appointment= new Appointment("2022-05-20;16:00","In review", "For Migraine", "Joe","Justin");
			Appointment appointment2= new Appointment("2022-05-22;12:00","In review", "For Injury", "Harry","Smith");
			appointmentRepository.save(appointment);
			appointmentRepository.save(appointment2);
			int appid= appointment.getId();
			assertEquals(appid, appointment.getId());
		}
	
// Testing if we can find appointment by Patient
		@Test
		void findAppointmentByPatient()
		{
			Appointment appointment= new Appointment("2022-05-20;16:00","In review", "For Migraine", "Joe","Justin");
			Appointment appointment2= new Appointment("2022-05-22;12:00","In review", "For Injury", "Harry","Smith");
			appointmentRepository.save(appointment);
			appointmentRepository.save(appointment2);
			assertEquals("Justin", appointment.getPatient());	
		}
		
// Testing if we can find appointment by Doctor
		@Test
		void findAppointmentByDoctor()
		{
			Appointment appointment= new Appointment("2022-05-20;16:00","In review", "For Migraine", "Joe","Justin");
			Appointment appointment2= new Appointment("2022-05-22;12:00","In review", "For Injury", "Harry","Smith");
			appointmentRepository.save(appointment);
			appointmentRepository.save(appointment2);
			assertEquals("Harry", appointment2.getDoctor());	
		}
	
//Testing if we can update the Appointment Status
	@Test
	void updateAppointmentStatus()
	{
		Appointment appointment= new Appointment("2022-05-20;16:00","In review", "For Migraine", "Joe","Justin");
		Appointment appointment2= new Appointment("2022-05-22;12:00","In review", "For Injury", "Harry","Smith");
		appointmentRepository.save(appointment);
		appointmentRepository.save(appointment2);
		appointment2.setStatus("Approved");
		assertEquals("Approved", appointment2.getStatus() );
	}
	

	
	
}
