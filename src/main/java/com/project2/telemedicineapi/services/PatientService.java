package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.LoginDTO;

import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.exception.BadParameterxception;
import com.project2.telemedicineapi.exception.DoctorsNotFound;
import com.project2.telemedicineapi.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public void createPatient(Patient incomingPatient) {
        patientRepository.save(incomingPatient);
    }
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(int id){
        return patientRepository.findById(id);
    }

    public Patient getPatient(int id)
    {
        return patientRepository.findById(id).get();

    }

    public Patient login(LoginDTO dto) throws BadParameterxception {
        if (dto.getUsername().trim().equals("") || dto.getPassword().trim().equals("")) {
            throw new BadParameterxception("Invalid input"); //need to add different exception
        }
        Patient doctor = patientRepository.findByUsernameAndPassword(dto.getUsername().trim(), dto.getPassword().trim());
        if (doctor == null) {
            throw new DoctorsNotFound("Invalid username or password");
        }
        return doctor;
    }









}