package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.AppointmentRequest;
import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repositories.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    /**
     * Create appointment
     * @param newAppointment - new appointment data transfer object
     */
    public void createAppointment(AppointmentRequest newAppointment) {
        Appointment appointment = new Appointment();

        Doctor doctor = doctorService.getDoctorByLastName(newAppointment.getDrLastName());
        Patient patient = patientService.getPatientByLastName(newAppointment.getPtLastName());
        appointment.setStatus("Pending");
        appointment.setDate(newAppointment.getDate());
        appointment.setTime(newAppointment.getTime());
        appointment.setNote("");
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
    }

    /**
     * Get all appointments
     */
    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }

    /**
     * Get appointment associated with doctor id
     * @param id - doctor id
     */
    public List<Appointment> getAppointmentsByDoctorId(int id){
        return appointmentRepository.getAppointmentByDoctorId(id);
    }

    /**
     * Get appointments associated with patient id
     * @param id - patient id
     */
    public List<Appointment> getAppointmentsByPatientId(int id){
        return appointmentRepository.getAppointmentByPatientId(id);
    }

    /**
     * Get appointment by id
     * @param id - appointment id
     */
    public Appointment getAppointment(int id) {
        return appointmentRepository.findById(id).get();
    }

    /**
     * Update appointment status
     * @param status
     */
    public void updateStatus(int id, String status){
        Appointment appointment = getAppointment(id);
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    //ADDED
    /**
     * Delete appointment with id
     * @param id - appointment id
     */
    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

    /**
     * Add note to appointment with associated id
     * @param id - appointment id
     * @param note - consult note
     */
    public void addNote(int id, String note){
        Appointment appointment = getAppointment(id);
        appointment.setNote(note);
        appointmentRepository.save(appointment);
    }
}
