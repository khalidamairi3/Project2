package com.project2.telemedicineapi.controllers;

import java.util.List;
import java.util.Optional;

import com.project2.telemedicineapi.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;

import com.project2.telemedicineapi.services.PatientService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity createPatient(@RequestBody Patient incomingPatient) {
        try{
            patientService.createPatient(incomingPatient);
            return ResponseEntity.accepted().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/all")
    public ResponseEntity viewAllPatients() {
        try{
            return ResponseEntity.ok(patientService.getAllPatients());
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity findPatientById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(patientService.getPatientById(id));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity editPatientProfile(@RequestBody Patient existingPatient) {
        try{
            patientService.createPatient(existingPatient);
            return ResponseEntity.accepted().build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }


    }
}