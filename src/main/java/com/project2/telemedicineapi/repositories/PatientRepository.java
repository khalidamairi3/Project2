package com.project2.telemedicineapi.repositories;

import com.project2.telemedicineapi.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
     Patient findByUsernameAndPassword(String username,String password);
}
