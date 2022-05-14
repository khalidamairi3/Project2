package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.LoginDTO;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.exception.BadParameterxception;
import com.project2.telemedicineapi.exception.PatientNotFound;
/*import com.project2.telemedicineapi.exception.UserNotLoggedInException;*/
import com.project2.telemedicineapi.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientrepository;


    public List<Patient> getallpatients()  {
        List<Patient> patients = patientrepository.findAll();
        return patients;

    }

    public Patient loginPatient(LoginDTO dto)  {
        if (dto.getUsername().trim().equals("") || dto.getPassword().trim().equals("")) {
            throw new PatientNotFound("Invalid input");
        }
        Patient patient = patientrepository.findByUsernameAndPassword(dto.getUsername().trim(), dto.getPassword().trim());
        if (patient == null) {
            throw new PatientNotFound("Invalid username or password");
        }
        return patient;

    }


    public Patient registerPatients(Patient patient) {

               Patient newPatient = patientrepository.save(patient);
               return newPatient;
            }
}