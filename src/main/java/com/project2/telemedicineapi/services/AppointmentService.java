package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.AppointmentRequest;
import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.helpers.NotificationClient;
import com.project2.telemedicineapi.repositories.AppointmentRepository;
import com.project2.telemedicineapi.repositories.DoctorRepository;
import com.project2.telemedicineapi.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    AppointmentRepository appointmentRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    NotificationClient notificationClient;

    @Autowired
    void setAppointmentRepository(AppointmentRepository appointmentRepository){
        this.appointmentRepository= appointmentRepository;
    }
    @Autowired
    void setDoctorRepository(DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
    }
    @Autowired
    void setPatientRepository(PatientRepository patientRepository){
        this.patientRepository= patientRepository;
    }
    @Autowired
    void setNotificationClient(NotificationClient notificationClient){this.notificationClient = notificationClient;}






    public void createAppointment(AppointmentRequest newAppointment) {
        Appointment appointment = new Appointment();
        Patient patient = patientRepository.getById(newAppointment.getPatientId());
        Doctor doctor = doctorRepository.getById(newAppointment.getDoctorId());
        appointment.setStatus("pending");
        appointment.setDateTime(newAppointment.getDateTime());
        appointment.setNote("");
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
        notificationClient.callPostEmail(doctor.getPhoneNum(),"Hey " + doctor.getUsername() + ", you have a new appointment request from "+  patient.getUsername() );
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
        return appointmentRepository.getById(id);

    }
    public void updateStatus(int id, String status){
        Appointment appointment = getAppointment(id);
        appointment.setStatus(status);
        NotificationClient notificationClient= new NotificationClient();
        notificationClient.callPostEmail(appointment.getPatient().getPhoneNum(),"Hey " + appointment.getPatient().getUsername() + ", your appointment on " + appointment.getDateTime() + " has been " +appointment.getStatus() +" by "+  appointment.getDoctor().getUsername() );
        appointmentRepository.save(appointment);
    }
    public void addNote(int id,String note){
        Appointment appointment = getAppointment(id);
        appointment.setNote(note);
        appointmentRepository.save(appointment);
    }


}
