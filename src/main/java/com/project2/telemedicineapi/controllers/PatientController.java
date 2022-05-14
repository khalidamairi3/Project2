package com.project2.telemedicineapi.controllers;

import com.project2.telemedicineapi.dto.LoginDTO;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.exception.BadParameterxception;
import com.project2.telemedicineapi.exception.UnAuthorizedResponse;
import com.project2.telemedicineapi.services.JwtService;
import com.project2.telemedicineapi.services.PatientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> loginPatient(@RequestBody LoginDTO dto) {
        try {
            Patient patient = patientService.loginPatient(dto);
            String jwt = jwtService.createPatientJwt(patient);
            HttpHeaders reposeHeader = new HttpHeaders();
            reposeHeader.set("token", jwt);
            return ResponseEntity.ok().headers(reposeHeader).body(patient);
        } catch (Exception ex1) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<?> registerPatients(@RequestBody Patient patient) {

           Patient newPatient = patientService.registerPatients(patient);
           return ResponseEntity.ok().body(newPatient);



    }
}

