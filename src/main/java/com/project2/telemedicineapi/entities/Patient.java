package com.project2.telemedicineapi.entities;


import javax.persistence.*;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name =  "patient", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Patient {
	
	
//  private String username;
//  private String password;//  private String firstName;
//  private String lastName;
//  private String dob;
//  private String phoneNum;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
	
	@Column(name = "username" , nullable = false)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "first_name" , nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "dob", nullable = false)
	private String dob;
	
	@Column(name = "phone_num", nullable = false)
	private String phoneNum;
	
	
	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(
//			name = "patient-doctor-List",
//		joinColumns = @JoinColumn(
//	            name = "doctor_id", referencedColumnName = "id"),
//		inverseJoinColumns = @JoinColumn(
//			            name = "patient_id", referencedColumnName = "id"))
//	private Collection<Doctor> doctors;
	
	
	public Patient() {
		super();
	}

	public Patient(String userName, String password, String firstName, String lastName, String dob, String phoneNum
			) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNum = phoneNum;
		//this.doctors = doctors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "Patient [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob=" + dob + ", phoneNum=" + phoneNum + "]";
	}

	

//	public Collection<Doctor> getDoctors() {
//		return doctors;
//	}
//
//	public void setDoctors(Collection<Doctor> doctors) {
//		this.doctors = doctors;
//	}

	
}


//}
