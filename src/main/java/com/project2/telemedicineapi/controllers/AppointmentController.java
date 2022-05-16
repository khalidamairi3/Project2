package com.project2.telemedicineapi.controllers;

import com.project2.telemedicineapi.dto.AppointmentRequest;
import com.project2.telemedicineapi.dto.AppointmentRequestDTO;
import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repositories.AppointmentRepository;
import com.project2.telemedicineapi.services.AppointmentService;
import com.project2.telemedicineapi.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity getAllAppointments(){
        try{
            return ResponseEntity.ok(appointmentService.getAll());
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("doctor/{id}")
    public ResponseEntity getAppointmentByDoctor(@PathVariable int id){
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(id));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("patient/{id}")
    public ResponseEntity getAppointmentByPatient(@PathVariable int id){
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(id));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }
    // Book a new appointment
    @PostMapping("/request")
    public ResponseEntity createAppointment(@RequestBody AppointmentRequest newAppointment) {

        try {
            appointmentService.createAppointment(newAppointment);
            return ResponseEntity.status(201).build();

        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    //Get Appointment By Id
    @GetMapping("{id}")
    public ResponseEntity findAppointmentById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(appointmentService.getAppointment(id));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }

    }

//
//    //Get All Appointments By Patient Name
//    //Get Patient By phoneNum
//    @GetMapping("/getbypatientname/{patient}")
//    public List<Appointment> findAppointmentByPatient(@PathVariable String patient) {
//        return appointmentRepository.getAppointmentByPatient(patient);
//    }
//
//    //Get All Appointments By Doctor Name
//    @GetMapping("/getbydoctorname/{doctor}")
//    public List<Appointment> findAppointmentByDoctor(@PathVariable String doctor) {
//        return appointmentRepository.getAppointmentByDoctor(doctor);
//    }
//
//    // Change status of an Appointment
//    @PostMapping("/update/status")
//    public void updateReimbursement(@RequestBody Appointment processedAppointment) {
//        appointmentRepository.save(processedAppointment);
//    }
}
