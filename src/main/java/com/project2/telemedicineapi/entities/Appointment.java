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

//Creating a Table "Appointments" in the database, with uniqueConstraint ==> dateTime
//& keeping id as a primary key
@Entity
@Table(name =  "Appointments", uniqueConstraints = @UniqueConstraint(columnNames = "dateTime"))
public class Appointment {
	
// Setting up  "id" as Primary Key
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
	
// Introducing column "dateTime" in "Appointments" Table	
	@Column(name = "dateTime" , nullable = false)
		private String dateTime;
	
// Introducing column "Status" in "Appointments" Table
	@Column(name = "Status", nullable = false)
	private String status;
	
// Introducing column "note" in "Appointments" Table
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

// No Args Constructor	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

// Constructor with field names	
	public Appointment(String dateTime, String status, String note, String doctor, String patient) {
		super();
		this.dateTime = dateTime;
		this.status = status;
		this.note = note;
		this.doctor = doctor;
		this.patient = patient;
	}

// Getter & Setter Methods for all fields  ======>	
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

// To String Method	
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
	
	