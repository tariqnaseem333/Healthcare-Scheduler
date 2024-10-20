package com.infy.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.infy.model.Appointment;
import com.infy.model.Doctor;
import com.infy.model.Patient;
import com.infy.model.Status;

public class HealthcareDAOImpl implements HealthcareDAO {
	
	private List<Doctor> doctorList = new ArrayList<>();
	private List<Appointment> bookedAppointments = new ArrayList<>();
	
	public HealthcareDAOImpl() {
		Doctor doctor1 = new Doctor(1001, "Dr. Smit", "Cardiology", "10:00AM-11:00AM", LocalDate.of(2025, 11, 26));
		Doctor doctor2 = new Doctor(1002, "Dr. Semi", "Dermatology", "12:00PM-01:00PM", LocalDate.of(2025, 9, 24));
		Doctor doctor3 = new Doctor(1003, "Dr. Adam", "Gynecology", "04:00PM-05:00PM", LocalDate.of(2025, 12, 27));
		Doctor doctor4 = new Doctor(1004, "Dr. John", "Cardiology", "02:00PM-03:00PM", LocalDate.of(2025, 10, 20));
		Doctor doctor5 = new Doctor(1005, "Dr. Jim", "Cardiology", "11:00PM-12:00PM", LocalDate.of(2025, 10, 15));
		Doctor doctor6 = new Doctor(1006, "Dr. Tim", "Dermatology", "03:00PM-04:00PM", LocalDate.of(2025, 12, 28));
		Doctor doctor7 = new Doctor(1007, "Dr. Flin", "Gynecology", "12:00PM-01:00PM", LocalDate.of(2025, 11, 25));
		Doctor doctor8 = new Doctor(1008, "Dr. Greg", "Dermatology", "10:00PM-11:00PM", LocalDate.of(2025, 11, 07));

		doctorList = List.of(doctor1, doctor2, doctor3, doctor4, doctor5, doctor6, doctor7, doctor8);
	}
	
	@Override
	public List<Doctor> getDoctorList() {
		return doctorList;
	}

	@Override
	public List<Doctor> findDoctors(String specialization) {
		return doctorList.stream()
						 .filter(doctor->doctor.getSpecialization().equalsIgnoreCase(specialization))
						 .collect(Collectors.toList()); 
	}
	
	@Override
	public Status bookAppointment(Appointment appointment, Patient patient) {
		Optional<Appointment> bookedAppointment = bookedAppointments.stream()
						 								      	    .filter(aptmnt-> aptmnt.getDoctorId().equals(appointment.getDoctorId()))
						 								      	    .findFirst();
		if(bookedAppointment.isPresent()) {
			appointment.setStatus(Status.NOT_CONFIRMED);
			return appointment.getStatus();
		} else {
			appointment.setStatus(Status.CONFIRMED);
			bookedAppointments.add(appointment);
			return appointment.getStatus();
		}
		
	}
	
}

