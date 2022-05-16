package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.AppointmentRequest;
import com.project2.telemedicineapi.dto.AppointmentRequestDTO;
import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentService appointmentService;

    public void createAppointment(AppointmentRequest newAppointment) {
        Appointment appointment = new Appointment();
        Patient patient = patientService.getPatient(newAppointment.getPatientId());
        appointment.setApproved(false);
        appointment.setDateTime(newAppointment.getDateTime());
        appointment.setNote("");
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDoctorId(int id) {
        return appointmentRepository.getAppointmentByDoctorId(id);

    }

    public List<Appointment> getAppointmentsByPatientId(int id) {
        return appointmentRepository.getAppointmentByPatientId(id);

    }

    public Appointment getAppointment(int id) {
        return appointmentRepository.findById(id).get();

    }


}
