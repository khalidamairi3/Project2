package com.project2.telemedicineapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repo.PatientRepository;

@SpringBootTest
public class PatientControllerTests {
	
	@Autowired
	private PatientRepository patientRepository;
	
	// Testing if a new Patient is created ==> Verified by searching by  Unique Username
	@Test
	void createPatient()
	{
		Patient patient= new Patient("rockstarJames","pwd4james", "James", "Potter",
			"06-September-1987", "6473392021"   );
patientRepository.save(patient);

assertEquals("rockstarJames", patient.getUserName());

System.out.println("Id of created patient is:" +patient.getId());
	}
	
	// Testing if we can retrieve a list of All Patients ==>  Returns a list of created Patients
	@Test
	void viewAllPatients()
	{
		Patient patient= new Patient("rockstarJames","pwd4james", "James", "Potter",
				"06-September-1987", "6473392021"   );
	patientRepository.save(patient);
	
	Patient patient2= new Patient("rockstarYash","pwd4yash", "Yash", "Chopra",
			"06-September-1984", "6473393032"   );
patientRepository.save(patient2);
	

assertEquals("[Patient [userName=rockstarJames, password=pwd4james, firstName=James, lastName=Potter, dob=06-September-1987, phoneNum=6473392021], Patient [userName=rockstarYash, password=pwd4yash, firstName=Yash, lastName=Chopra, dob=06-September-1984, phoneNum=6473393032]]",
	patientRepository.findAll());

	}
	
// Testing if we can retrieve a patient By PhoneNum
	@Test
	void findPatientByPhoneNum()
	{
		Patient patient= new Patient("rockstarJames","pwd4james", "James", "Potter",
				"06-September-1987", "6473392021"   );
patientRepository.save(patient);
    assertEquals("6473392021", patient.getPhoneNum());		
		
	}
	
// Testing - if a Patient Profile can be edited
	@Test
	void editPatientProfile()
	{
		Patient patient= new Patient("rockstarJames","pwd4james", "James", "Potter",
				"06-September-1987", "6473392021"   );
patientRepository.save(patient);
patient.setPhoneNum("3437777076");
    assertEquals("3437777076", patient.getPhoneNum());		
		
	}
	
	
	// For Deleting the repeating Test Data After Each Test
	@AfterEach
	void teardown()
	{
		System.out.println("tearing down");
		patientRepository.deleteAll();
	}
	
	
	

	
}
