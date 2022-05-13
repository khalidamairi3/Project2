package com.project2.telemedicineapi.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name =  "Appointments", uniqueConstraints = @UniqueConstraint(columnNames = "dateTime"))
public class Appointment {
	
	
//  private String username;
//  private String password;//  private String firstName;
//  private String lastName;
//  private String dob;
//  private String phoneNum;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
	
	@Column(name = "dateTime" , nullable = false)
	private String dateTime;
	
	@Column(name = "Status", nullable = false)
	private String status;
	
	@Column(name = "note" , nullable = false)
	private String note;
	
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="doctor_id", referencedColumnName = "id")
	//@Column(name = "doctor" , nullable = false)
	private String doctor;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="patient_id", referencedColumnName = "id")
    //@Column(name = "patient" , nullable = false)
	private String patient;

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(String dateTime, String status, String note, String doctor, String patient) {
		super();
		this.dateTime = dateTime;
		this.status = status;
		this.note = note;
		this.doctor = doctor;
		this.patient = patient;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Appointment [dateTime=" + dateTime + ", status=" + status + ", note=" + note + ", doctor=" + doctor
				+ ", patient=" + patient + "]";
	}
  

	
	
	
	
}




	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(
//			name = "appointment",
//			joinColumns = @JoinColumn(
//		            name = "user_id", referencedColumnName = "id"),
//			inverseJoinColumns = @JoinColumn(
//				            name = "role_id", referencedColumnName = "id"))
	
	//private Collection<Doctor> doctors;
	
	