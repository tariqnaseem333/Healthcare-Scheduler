package com.infy.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	
	private Integer doctorId;
	private String doctorName;
	private String specialization;
	private String availableTime;
	private LocalDate availableDate;
	
}
