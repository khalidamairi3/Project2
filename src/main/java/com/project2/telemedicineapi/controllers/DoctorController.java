package com.project2.telemedicineapi.controllers;

import com.project2.telemedicineapi.dto.LoginDTO;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.exception.UnAuthorizedResponse;
import com.project2.telemedicineapi.services.DoctorService;
import com.project2.telemedicineapi.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {

        Doctor doctor = doctorService.login(dto);
        String jwt = jwtService.createJwt(doctor);

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("token", jwt);
        return ResponseEntity.ok().body(jwt);

    }

    @GetMapping
    public ResponseEntity<?> getalldoctors(@RequestHeader("Authorization") String jwt) throws UnAuthorizedResponse {
        if (!jwt.equals(null) && !jwt.equals("")) {
            jwt = jwt.split(" ")[1];
            Jws<Claims> token = null;
                token = jwtService.parseJwt(jwt);
                if (token != null) {
                    List<Doctor> doctor = doctorService.getalldoctors();
                    return ResponseEntity.ok().body(doctor);

                }
                return ResponseEntity.status(403).body("Invalid Username or password");

            } else{
                return ResponseEntity.status(400).body("You need to Login");
            }
        }
    }
