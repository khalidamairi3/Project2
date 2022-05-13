package com.project2.telemedicineapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	Patient getPatientByphoneNum( String phoneNum);


	

	
}