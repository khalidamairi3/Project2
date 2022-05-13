package com.project2.telemedicineapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project2.telemedicineapi.entities.Appointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> getAppointmentByPatient(String patient);

	List<Appointment> getAppointmentByDoctor(String doctor);


}
