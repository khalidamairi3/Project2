package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.AppointmentDTO;
import com.project2.telemedicineapi.dto.AppointmentRequest;
import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.repositories.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void createAppointment(AppointmentRequest newAppointment) {
        Appointment appointment = new Appointment();
//        Doctor doctor = doctorService.getDoctorById(newAppointment.getDoctorId());
//        Patient patient = patientService.getPatient(newAppointment.getPatientId());
//
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

    public List<Appointment> getAll(){
        return appointmentRepository.findAll();
    }
    public List<Appointment> getAppointmentsByDoctorId(int id){
        return appointmentRepository.getAppointmentByDoctorId(id);
    }
    public List<Appointment> getAppointmentsByPatientId(int id){
        return appointmentRepository.getAppointmentByPatientId(id);

    }
    public Appointment getAppointment(int id) {
        return appointmentRepository.findById(id).get();
    }

//    /**
//     * Converts and updates the incoming dto into a reimbursement instance
//     * Creates an email to notify the employee and manager of the changes
//     * @param dto - the reimbursement data transfer object
//     */
//    public void updateEntity(AppointmentDTO dto, int id) {
//        int doctorId = dto.getDoctorId();
//        int patientId = dto.getPatientId();
//        String date = dto.getDate();
//        String time = dto.getTime();
//        String note = dto.getNote();
//        String status = dto.getStatus();
//
////        logger.debug("Status: {}", managerRepository.findByFullName(managerName));
////        logger.debug("Find manger via full name: {}", dto.getStatus());
////        logger.debug("Reimbursement id: {}", id);
//
//        appointmentRepository.updateAppointmentById(id, date, time, note, status);
//    }

    public void updateStatus(int id, String status){
        Appointment appointment = getAppointment(id);
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }
}
