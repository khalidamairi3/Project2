package com.project2.telemedicineapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.telemedicineapi.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>  {

}
