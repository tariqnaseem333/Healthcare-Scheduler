package com.infy.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.infy.exception.HealthcareException;
import com.infy.model.Appointment;
import com.infy.model.Patient;
import com.infy.service.HealthcareService;
import com.infy.service.HealthcareServiceImpl;

public class HealthcareTest {
	
	HealthcareService service = new HealthcareServiceImpl(); 
	
	// User Story 2: FetchDoctors By Specialization Unit Test case
	@Test
	public void fetchDoctorInValidSpecializationTest() {
		String specialization = "Chiropractor";
		HealthcareException exception = Assertions.assertThrows(HealthcareException.class, () -> service.fetchDoctor(specialization));
		Assertions.assertSame("Service.DOCTOR_NOT_FOUND", exception.getMessage());
	}
	
	// User Story 3: BookAppointment Unit Test cases
	@Test
	public void bookAppointmentInValidNameTest() {
		Patient patient = new Patient(2001, "Sam123", "Male", "High BP", 9898987656L, "10:00AM", LocalDate.of(2025, 11, 26));
		Appointment appointment = new Appointment(1001, 2001, "10:00AM", LocalDate.of(2025, 11, 26));
		HealthcareException exception = Assertions.assertThrows(HealthcareException.class, () -> service.bookAnAppointment(patient, appointment));
		Assertions.assertSame("Validator.INVALID_PATIENT_NAME", exception.getMessage());
	}
	
	@Test
	public void bookAppointmentInValidContactNumberTest() {
		Patient patient = new Patient(2001, "Sam", "Male", "High BP", 1234L, "10:00AM", LocalDate.of(2025, 11, 26));
		Appointment appointment = new Appointment(1001, 2001, "10:00AM", LocalDate.of(2025, 11, 26));
		HealthcareException exception = Assertions.assertThrows(HealthcareException.class, () -> service.bookAnAppointment(patient, appointment));
		Assertions.assertSame("Validator.INVALID_CONTACT_NUMBER", exception.getMessage());
	}
	
	@Test
	public void bookAppointmentInValidGenderTest() {
		Patient patient = new Patient(2001, "Sam", "Trans", "High BP", 9898987656L, "10:00AM", LocalDate.of(2025, 11, 26));
		Appointment appointment = new Appointment(1001, 2001, "10:00AM", LocalDate.of(2025, 11, 26));
		HealthcareException exception = Assertions.assertThrows(HealthcareException.class, () -> service.bookAnAppointment(patient, appointment));
		Assertions.assertSame("Validator.INVALID_GENDER", exception.getMessage());
	}
	
	@Test
	public void bookAppointmentInValidIssuesTest() {
		Patient patient = new Patient(2001, "Sam", "Male", "", 9898987656L, "10:00AM", LocalDate.of(2025, 11, 26));
		Appointment appointment = new Appointment(1001, 2001, "10:00AM", LocalDate.of(2025, 11, 26));
		HealthcareException exception = Assertions.assertThrows(HealthcareException.class, () -> service.bookAnAppointment(patient, appointment));
		Assertions.assertSame("Validator.INVALID_PATIENT_ISSUE", exception.getMessage());
	}
	
	@Test
	public void bookAppointmentInValidAppointmentDateTest() {
		Patient patient = new Patient(2001, "Sam", "Male", "High BP", 9898987656L, "10:00AM", LocalDate.of(2023, 11, 26));
		Appointment appointment = new Appointment(1001, 2001, "10:00AM", LocalDate.of(2025, 11, 26));
		HealthcareException exception = Assertions.assertThrows(HealthcareException.class, () -> service.bookAnAppointment(patient, appointment));
		Assertions.assertSame("Validator.INVALID_APPOINTMENT_DATE", exception.getMessage());
	}
	
	@Test
	public void bookAppointmentInValidAppointmentTimeTest() {
		Patient patient = new Patient(2001, "Sam", "Male", "High BP", 9898987656L, "02:00AM", LocalDate.of(2025, 11, 26));
		Appointment appointment = new Appointment(1001, 2001, "10:00AM", LocalDate.of(2025, 11, 26));
		HealthcareException exception = Assertions.assertThrows(HealthcareException.class, () -> service.bookAnAppointment(patient, appointment));
		Assertions.assertSame("Validator.INVALID_APPOINTMENT_TIME", exception.getMessage());
	}
	
}

