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

// Creating a Table "patient" in the database, with uniqueConstraint ==> username
//  & keeping id as a primary key
@Entity
@Table(name =  "patient", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Patient {
	
// Setting up  "id" as Primary Key
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private int id;
	
// Introducing column "username" in "patient" Table
	@Column(name = "username" , nullable = false)
	private String userName;
	
// Introducing column "password" in "patient" Table
	@Column(name = "password", nullable = false)
	private String password;

// Introducing column "first_name" in "patient" Table	
	@Column(name = "first_name" , nullable = false)
	private String firstName;
	
// Introducing column "last_name" in "patient" Table	
	@Column(name = "last_name", nullable = false)
	private String lastName;

// Introducing column "dob" in "patient" Table
	@Column(name = "dob", nullable = false)
	private String dob;

// Introducing column "phone_num" in "patient" Table	
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
	
	
// No Args Constructor	
	public Patient() {
		super();
	}

// Constructor with field names	
	public Patient(String userName, String password, String firstName, String lastName, String dob, String phoneNum
			) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phoneNum = phoneNum;
		
	}

// Getter & Setter Methods for all fields  ======>	
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

	// To String Method
	@Override
	public String toString() {
		return "Patient [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob=" + dob + ", phoneNum=" + phoneNum + "]";
	}

	
}


