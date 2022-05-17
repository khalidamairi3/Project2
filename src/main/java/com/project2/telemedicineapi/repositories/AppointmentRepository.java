package com.project2.telemedicineapi.repositories;

import com.project2.telemedicineapi.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> getAppointmentByPatientId(int id);

    List<Appointment> getAppointmentByDoctorId(int id);

//    /**
//     * Updates the appointment instance
//     * @param id - id of the appointment
//     * @param date - date of appointment
//     * @param time - time of appointment
//     * @param status - status of appointment
//     * @param note - consultation note for appointment
//     */
//    @Modifying
//    @Query("update Appointment a set a.date = ?1, a.time = ?2, a.note = ?3, a.status = 4? where r.id = ?5")
//    void updateAppointmentById(int id, String date, String time, String note, String status);
}
