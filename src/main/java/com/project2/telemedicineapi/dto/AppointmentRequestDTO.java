package com.project2.telemedicineapi.dto;

import com.project2.telemedicineapi.entities.Appointment;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AppointmentRequestDTO {
    private int doctorID;
    private int PatientID;
    private Appointment appointment;
}