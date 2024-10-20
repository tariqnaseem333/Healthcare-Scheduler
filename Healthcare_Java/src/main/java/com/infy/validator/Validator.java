package com.infy.validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.commons.logging.LogFactory;
import com.infy.exception.HealthcareException;
import com.infy.model.Patient;

public class Validator {
	
	public void validatePatient(Patient patient) throws HealthcareException {
		String errorMessage = null;
		
		if(!validatePatientName(patient.getPatientName()))
			errorMessage = "Validator.INVALID_PATIENT_NAME";
		else if(!validatePatientContactNumber(patient.getContactNumber()))
			errorMessage = "Validator.INVALID_CONTACT_NUMBER";
		else if(!validatePatientGender(patient.getGender()))
			errorMessage = "Validator.INVALID_GENDER";
		else if(!validatePatientIssues(patient.getPatientIssues()))
			errorMessage = "Validator.INVALID_PATIENT_ISSUE";
		else if(!validatePatientAppointmentTime(patient.getAppointmentTime()))
			errorMessage = "Validator.INVALID_APPOINTMENT_TIME";
		else if(!validatePatientAppointmentDate(patient.getAppointmentDate()))
			errorMessage = "Validator.INVALID_APPOINTMENT_DATE";
		
		if(errorMessage != null) {
			HealthcareException exception = new HealthcareException(errorMessage);
			LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
			throw exception;
		}
	}
	
	public Boolean validatePatientName(String patientName) {
		return patientName.matches("[A-Z][a-z]+([ ][A-Z][a-z]+)*");
	}
	
	public Boolean validatePatientContactNumber(Long patientContactNumber) {
		boolean areAllDigitsSame = true;
		
		String str = String.valueOf(patientContactNumber);
		for(int i=0; i<str.length()-1; i++) {
			if(str.charAt(i) != str.charAt(i+1)) {
				areAllDigitsSame = false;
				break;
			}
		}
		
		return patientContactNumber.toString().length()==10 && !areAllDigitsSame;
	}
	
	public Boolean validatePatientGender(String patientGender) {
		return patientGender.matches("Male|Female|Others");
	}
	
	public Boolean validatePatientIssues(String patientIssues) {
		return patientIssues != null && !patientIssues.isEmpty();
	}
	
	public Boolean validatePatientAppointmentTime(String patientAppointmentTime) {
		DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("hh:mm[a]").withLocale(Locale.US);
		LocalTime patientAppointmentLocalTime = LocalTime.parse(patientAppointmentTime, parseFormat);		
		LocalTime startAppointmentTime = LocalTime.of(10, 0);
		LocalTime endAppointmentTime = LocalTime.of(20, 0);
		
		return ( patientAppointmentLocalTime.toString().equals(startAppointmentTime.toString()) || patientAppointmentLocalTime.isAfter(startAppointmentTime) ) &&  
				( patientAppointmentLocalTime.toString().equals(endAppointmentTime.toString()) || patientAppointmentLocalTime.isBefore(endAppointmentTime) );
	}
	
	public Boolean validatePatientAppointmentDate(LocalDate patientAppointmentDate) {
		return patientAppointmentDate.isAfter(LocalDate.now()) || patientAppointmentDate.equals(LocalDate.now());
	}
	
}

