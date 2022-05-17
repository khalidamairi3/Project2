package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.LoginDTO;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.exception.BadParameterxception;
import com.project2.telemedicineapi.exception.DoctorsNotFound;
import com.project2.telemedicineapi.repositories.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {
    Logger logger = LoggerFactory.getLogger(DoctorService.class);
    @Autowired
    DoctorRepository doctorrepository;

    /**
     *
     * @return return doctor object to the controller
     * @throws DoctorsNotFound if there is no doctor list found throws the exception
     * @List provide the collection of doctors list
     */

    public List<Doctor> getalldoctors() throws DoctorsNotFound {
        logger.info("getallDoctors methods invocked");
        List<Doctor> doctor = doctorrepository.findAll();
        return doctor;
    }

    public Doctor login(LoginDTO dto) throws BadParameterxception {

        logger.info("invocked login method for the doctor login");
        if (dto.getUsername().trim().equals("") || dto.getPassword().trim().equals("")) {
            throw new BadParameterxception("Invalid input"); //need to add different exception
        }
        Doctor doctor = doctorrepository.findByUsernameAndPassword(dto.getUsername().trim(), dto.getPassword().trim());
        if (doctor == null) {
            throw new DoctorsNotFound("Invalid username or password");
        }
        return doctor;
    }
}
