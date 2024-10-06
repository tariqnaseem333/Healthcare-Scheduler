package com.infy.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Appointment {
	
	private Integer appointmentId;
	private Integer doctorId;
	private Integer patientId;
	private String appointmentTime;
	private LocalDate appointmentDate;
	private Status status;
	
	private static int appointmentIdCounter;
	static {
		appointmentIdCounter = 3001;
	}
	
	public Appointment(Integer doctorId, Integer patientId, String appointmentTime,
			LocalDate appointmentDate) {
		this.appointmentId = appointmentIdCounter++;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.appointmentTime = appointmentTime;
		this.appointmentDate = appointmentDate;
	}
	
}

