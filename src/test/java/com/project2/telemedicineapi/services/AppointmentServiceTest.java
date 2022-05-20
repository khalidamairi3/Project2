package com.project2.telemedicineapi.services;

import com.project2.telemedicineapi.dto.AppointmentRequest;
import com.project2.telemedicineapi.entities.Appointment;
import com.project2.telemedicineapi.entities.Doctor;
import com.project2.telemedicineapi.entities.Patient;
import com.project2.telemedicineapi.helpers.NotificationClient;
import com.project2.telemedicineapi.repositories.AppointmentRepository;
import com.project2.telemedicineapi.repositories.DoctorRepository;
import com.project2.telemedicineapi.repositories.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppointmentServiceTest {

    private  AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private AppointmentService appointmentService;


    @BeforeEach
    public void initBeforeTest(){
        appointmentRepository = mock(AppointmentRepository.class);
        doctorRepository = mock(DoctorRepository.class);
        patientRepository = mock(PatientRepository.class);
        appointmentService = new AppointmentService();
        appointmentService.setAppointmentRepository(appointmentRepository);
        appointmentService.setDoctorRepository(doctorRepository);
        appointmentService.setPatientRepository(patientRepository);

    }

    @Test
    void createAppointment() {
        Doctor doctor2 = new Doctor(2, "Subhana", "password", "Subhana", "Menk", "Cardio surgeon", "+1 6474023789");
        doctorRepository.save(doctor2);
        Patient patient1 = new Patient(1, "Ayesha", "password", "Ayesha", "Solanki", "2000-05-12", "+1 6474023382");
        patientRepository.save(patient1);
        AppointmentRequest appointmentRequest = new AppointmentRequest(2,1,"2022-05-18 12:00:00");
        appointmentService.createAppointment(appointmentRequest);
        Appointment appointment = new Appointment(1,"2022-05-18 12:00:00","pending","",doctor2,patient1);
        when(appointmentRepository.getById(1)).thenReturn(appointment);
        Appointment actual = appointmentService.getAppointment(1);
        Assertions.assertEquals(appointment,actual);


    }

    @Test
    void getAll() {

        when(appointmentRepository.findAll()).thenReturn(Collections.emptyList());
        List<Appointment> actual = appointmentService.getAll();
        assertTrue(actual.isEmpty());
    }

    @Test
    void getAppointmentsByDoctorId() {
        Doctor doctor2 = new Doctor(2, "Subhana", "password", "Subhana", "Menk", "Cardio surgeon", "+1 6474023789");
        Patient patient1 = new Patient(1, "Ayesha", "password", "Ayesha", "Solanki", "2000-05-12", "+1 6474023382");
        Appointment appointment = new Appointment(1,"2022-05-18 12:00:00","pending","",doctor2,patient1);
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        when(appointmentRepository.getAppointmentByDoctorId(2)).thenReturn(appointments);
        List <Appointment> actual = appointmentService.getAppointmentsByDoctorId(2);
        Assertions.assertEquals(appointments,actual);
    }

    @Test
    void getAppointmentsByPatientId() {
        Doctor doctor2 = new Doctor(2, "Subhana", "password", "Subhana", "Menk", "Cardio surgeon", "+1 6474023789");
        Patient patient1 = new Patient(1, "Ayesha", "password", "Ayesha", "Solanki", "2000-05-12", "+1 6474023382");
        AppointmentRequest appointmentRequest = new AppointmentRequest(2,1,"2022-05-18 12:00:00");
        Appointment appointment = new Appointment(1,"2022-05-18 12:00:00","pending","",doctor2,patient1);
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        when(appointmentRepository.getAppointmentByPatientId(1)).thenReturn(appointments);
        List <Appointment> actual = appointmentService.getAppointmentsByPatientId(1);
        Assertions.assertEquals(appointments,actual);
    }

    @Test
    void getAppointment() {
    }

    @Test
    void updateStatus() {
    }

    @Test
    void addNote() {
        Doctor doctor2 = new Doctor(2, "Subhana", "password", "Subhana", "Menk", "Cardio surgeon", "+1 6474023789");
        Patient patient1 = new Patient(1, "Ayesha", "password", "Ayesha", "Solanki", "2000-05-12", "+1 6474023382");
        Appointment appointment = new Appointment(1,"2022-05-18 12:00:00","pending","",doctor2,patient1);
        when(appointmentRepository.getById(1)).thenReturn(appointment);
        appointment.setNote("test note");
        appointmentService.addNote(1,"test note");
        Appointment actual = appointmentService.getAppointment(1);
        Assertions.assertEquals(appointment,actual);
    }
}