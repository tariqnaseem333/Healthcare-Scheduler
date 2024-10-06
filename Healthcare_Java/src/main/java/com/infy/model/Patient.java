package com.infy.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	
	private Integer patientId;
	private String patientName;
	private String gender;
	private String patientIssues;
	private Long contactNumber;
	private String appointmentTime;
	private LocalDate appointmentDate;
	
}

