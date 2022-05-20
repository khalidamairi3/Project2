package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.LoginDTO;

import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.exception.BadParameterxception;
import com.project2.telemedicineapi.exception.DoctorsNotFound;
import com.project2.telemedicineapi.exception.PatientNotFound;
import com.project2.telemedicineapi.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {


    PatientRepository patientRepository;
    @Autowired
    public void setPatientRepository(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    public void createPatient(Patient incomingPatient) {
        patientRepository.save(incomingPatient);
    }
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }


    public Patient getPatient(int id)
    {
        return patientRepository.getById(id);

    }

    public Patient login(LoginDTO dto) throws BadParameterxception {
        if (dto.getUsername().trim().equals("") || dto.getPassword().trim().equals("")) {
            throw new BadParameterxception("Invalid input"); //need to add different exception
        }
        Patient patient = patientRepository.findByUsernameAndPassword(dto.getUsername().trim(), dto.getPassword().trim());
        if (patient == null) {
            throw new PatientNotFound("Invalid username or password");
        }
        return patient;
    }


}