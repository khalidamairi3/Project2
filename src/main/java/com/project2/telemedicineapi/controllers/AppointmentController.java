package com.project2.telemedicineapi.controllers;

import com.project2.telemedicineapi.dto.AppointmentDTO;
import com.project2.telemedicineapi.dto.AppointmentRequest;
import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.exception.UnAuthorizedResponse;
import com.project2.telemedicineapi.exception.UnauthorizedExeption;
import com.project2.telemedicineapi.repositories.AppointmentRepository;
import com.project2.telemedicineapi.services.AppointmentService;
import com.project2.telemedicineapi.services.JwtService;
import com.project2.telemedicineapi.services.PatientService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    JwtService jwtService;

    /**
     * Get all appointments
     * @return all appointment instances
     */
    @GetMapping("/all")
    public ResponseEntity getAllAppointments(@RequestHeader("Authorization") String jwt){
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


                    if (token != null && (role.equals("doctor"))) {
                        return ResponseEntity.ok(appointmentService.getAll());
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


    /**
     * Get all appointments associated with doctor ID
     * @param id - doctor id
     * @return appointment instances
     */
    @GetMapping("doctor/{id}")
    public ResponseEntity getAppointmentByDoctor(@PathVariable int id,@RequestHeader("Authorization") String jwt){
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


                    if (token != null && (role.equals("doctor"))) {
                        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(id));
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

    /**
     * Get all appointments associated with patient ID
     * @param id - patient id
     * @return appointment instances
     */
//    @GetMapping("patient/{id}")
//    public ResponseEntity getAppointmentByPatient(@PathVariable int id){
//        try {
//            return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(id));
//        }catch (Exception ex){
//            return ResponseEntity.notFound().build();
//        }
//    }
    @GetMapping("patient/{id}")
    public ResponseEntity getAppointmentByPatient(@PathVariable int id,@RequestHeader("Authorization") String jwt){
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

                    if (token != null && (role.equals("patient"))) {
                        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(id));
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

    /**
     * Make a new appointment
     * @param newAppointment - new appointment DTO
     */
    @PostMapping("/request")
    public ResponseEntity createAppointment(@RequestBody AppointmentRequest newAppointment) {

        try {
            appointmentService.createAppointment(newAppointment);
            return ResponseEntity.status(201).build();

        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
//    @PostMapping("/request")
//    public ResponseEntity createAppointment(@RequestBody AppointmentRequest newAppointment,@RequestHeader("Authorization") String jwt) {
//
//        if (!jwt.equals(null) && !jwt.equals("")) {
//            try {
//
//                jwt = jwt.split(" ")[1];
//                Jws<Claims> token = null;
//                try {
//                    token = jwtService.parseJwt(jwt);
//                    String role = null;
//                    try {
//                        role = token.getBody().get("user_id").toString().split(" ")[0];
//                    } catch (UnauthorizedExeption r) {
//                        return ResponseEntity.status(500).body("Invalid User Information");
//                    }
//
//
//                    if (token != null && (role.equals("patient"))) {
//                        appointmentService.createAppointment(newAppointment);
//                        return ResponseEntity.status(201).build();
//                    } else {
//                        return ResponseEntity.status(403).body("You are not authorized at this point");
//                    }
//                } catch (UnAuthorizedResponse e) {
//                    return ResponseEntity.status(500).body("Invalid Token");
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                return ResponseEntity.status(400).body("You need to Login");
//            }
//
//        }
//        return ResponseEntity.status(500).body("Internal Error");
//
//    }

    /**
     * Get appointment by ID
     * @param id - appointment id
     * @return appointment instance
     */
    @GetMapping("{id}")
    public ResponseEntity findAppointmentById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(appointmentService.getAppointment(id));
        }catch (Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Update appointment status
     * @param id appointment id
     * @param jwt jwt token
     * @return status code
     */
    @PutMapping("/{id}/seen")
    public ResponseEntity seenStatus(@PathVariable int id,@RequestHeader("Authorization") String jwt){
        if (!jwt.equals(null) && !jwt.equals("")) {
            try {

                System.out.println(jwt);
                jwt = jwt.split(" ")[1];
                System.out.println(jwt);
                Jws<Claims> token = null;
                try {
                    token = jwtService.parseJwt(jwt);
                    String role = null;
                    try {
                        role = token.getBody().get("user_id").toString().split(" ")[0];
                    } catch (UnauthorizedExeption r) {
                        return ResponseEntity.status(500).body("Invalid User Information");
                    }
                    System.out.println(jwt);

                    if (token != null && (role.equals("doctor"))) {
                        appointmentService.updateStatus(id,"seen");
                        return ResponseEntity.status(202).build();
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

    /**
     * Update appointment status
     * @param id appointment id
     * @param jwt jwt token
     * @return status code
     */
    @PutMapping("/{id}/confirm")
    public ResponseEntity confirmStatus(@PathVariable int id,@RequestHeader("Authorization") String jwt){
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

                    if (token != null && (role.equals("doctor"))) {
                        appointmentService.updateStatus(id,"confirmed");
                        return ResponseEntity.status(202).build();
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

    @DeleteMapping("/request/{id}")
    public void deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
    }


//    /**
//     * Update appointment
//     * @param appointmentDTO - the data transfer object
//     * @param id - appointment id
//     */
//    @PutMapping("requests/{id}")
//    public void updateReimbursement(
//            @RequestBody AppointmentDTO appointmentDTO,
//            @PathVariable int id
//    ) {
//        appointmentService.updateEntity(appointmentDTO, id);
//    }
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