package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.LoginDTO;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.exception.DoctorsNotFound;
import com.project2.telemedicineapi.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorrepository;

    public List<Doctor> getalldoctors() throws DoctorsNotFound {
        List<Doctor> doctor = doctorrepository.findAll();
        return doctor;
    }
    public Doctor login(LoginDTO dto){
        if(dto.getUsername().trim().equals("") || dto.getPassword().trim().equals("")){
            throw new DoctorsNotFound("Invalid input"); //need to add different exception
        }
        Doctor doctor = doctorrepository.findByUsernameAndPassword(dto.getUsername().trim(),dto.getPassword().trim());
        if(doctor==null){
            throw new DoctorsNotFound("Invalid username or password");
        }
        return doctor;
    }
}
