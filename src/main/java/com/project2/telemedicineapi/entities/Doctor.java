package com.project2.telemedicineapi.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Doctors")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @Column(name = "id", columnDefinition = "AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String specialization;
    private String phoneNum;

}
