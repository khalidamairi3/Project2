package com.project2.telemedicineapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    private int doctorId;
    private int patientId;
    private String dateTime;

}
