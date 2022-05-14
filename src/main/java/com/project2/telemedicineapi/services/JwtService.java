package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.exception.UnAuthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;


@Service
public class JwtService {
    private static JwtService instance;
    private Key key;

    public JwtService(){
        byte[] secret = "my_secret_passwordhkjhkgkgjhghjgkjgkgfgftufutfdsfsdfsdfgsdgsdgdgsdg".getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }

    // singleton implementation
    public static JwtService getInstance() {
        if (JwtService.instance == null) {
            JwtService.instance = new JwtService();
        }

        return JwtService.instance;
    }

    // sign a JWT with the key
    public String createJwt(Doctor doctor) {
        return Jwts.builder().setSubject(doctor.getUsername())
                .claim("user_id", "doctor "+doctor.getId())
                .signWith(key)
                .compact();
    }
    public String createPatientJwt(Patient patient) {
        return Jwts.builder().setSubject(patient.getUsername())
                .claim("user_id", "patient "+patient.getId())
                .signWith(key)
                .compact();
    }

    // validate a JWT with the key
    public Jws<Claims> parseJwt(String jwt) throws UnAuthorizedResponse {
        try {
            String id = String.valueOf(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt));
            System.out.println("value:  "+id);
            return Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
        } catch(JwtException e) {
            throw new UnAuthorizedResponse("The JWT is not valid.");
        }
    }
}
