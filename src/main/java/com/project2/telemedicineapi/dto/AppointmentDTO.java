package com.project2.telemedicineapi.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private int doctorId;
    private int patientId;
    private String date;
    private String time;
    private String status;
    private String note;
}