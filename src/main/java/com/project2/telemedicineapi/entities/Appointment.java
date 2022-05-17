package com.project2.telemedicineapi.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Appointments")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @Column(name = "id", columnDefinition = "AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String time;
    private String status;
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="patient_id", referencedColumnName = "id")
    private Patient patient;

}
