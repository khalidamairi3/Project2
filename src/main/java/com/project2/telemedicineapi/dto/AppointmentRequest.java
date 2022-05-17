package com.project2.telemedicineapi.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    private String drLastName;
    private String ptLastName;
    private String date;
    private String time;
}