package com.project2.telemedicineapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project2.telemedicineapi.dto.LoginDTO;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.exception.UnAuthorizedResponse;

import com.project2.telemedicineapi.exception.UnauthorizedExeption;
import com.project2.telemedicineapi.services.DoctorService;
import com.project2.telemedicineapi.services.JwtService;
import com.project2.telemedicineapi.services.PatientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ADDED: CrossOrigin annotation to allow front end browser call
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @Autowired
    JwtService jwtService;

    /**
     * Send post request to login as a doctor
     * @param dto - login data transfer object
     * @return status and header (for token)
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {

        logger.info("Doctor login info: {}", doctorService.login(dto));

        try {
            Doctor doctor = doctorService.login(dto);
            String jwt = jwtService.createJwt(doctor);
            HttpHeaders reposeHeader = new HttpHeaders();
            reposeHeader.set("token", jwt);
            return ResponseEntity.ok().headers(reposeHeader).body(doctor); /* Components oof HttpResponse method
                                                                                3. body(optional) in this case we are returning doctor object in response body*/
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }

    }

    // CHANGED!!
        // renamed to getAllPatients (to proper snakeCase)
    /**
     * Get all patients
     * @param jwt token
     * @return all patient instances
     */
    @GetMapping("/getAllPatients")
    public ResponseEntity<?> getAllPatients(@RequestHeader("Authorization") String jwt) {

        logger.info("Get all patients: {}", patientService.getAllPatients());

        if (!jwt.equals(null) && !jwt.equals("")) {
            try {

                jwt = jwt.split(" ")[1];
                Jws<Claims> token = null;
                try {
                    token = jwtService.parseJwt(jwt);
                    String role = null;
                    try {
                        role = token.getBody().get("user_id").toString().split(" ")[0];
                    } catch (UnauthorizedExeption r) {
                        return ResponseEntity.status(500).body("Invalid User Information");
                    }


                    if (token != null && role.equals("doctor")) {
                        List<Patient> patient = patientService.getAllPatients();
                        return ResponseEntity.ok().body(patient);
                    } else {
                        return ResponseEntity.status(403).body("You are not authorized at this point");
                    }
                } catch (UnAuthorizedResponse e) {
                    return ResponseEntity.status(500).body("Invalid Token");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return ResponseEntity.status(400).body("You need to Login");
            }

        }
        return ResponseEntity.status(500).body("Internal Error");
    }

    // CHANGED!!
        // REMOVED JWT (so that everyone can see doctor network),
        // renamed to getAllDoctors (to proper snakeCase)
        // added log
    /**
     * Get all doctors
     * @return all doctor instances
     */
    @GetMapping("/getAllDoctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        logger.info("Get all doctors: {}", doctorService.getAllDoctors());
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
