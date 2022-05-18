package com.project2.telemedicineapi.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {
    private String drLastName;  //CHANGED from id
    private String ptLastName;  //CHANGED from id
    private String date;
    private String time;
}