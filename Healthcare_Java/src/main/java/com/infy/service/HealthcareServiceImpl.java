package com.infy.service;

import java.util.List;

import com.infy.dao.HealthcareDAO;
import com.infy.exception.HealthcareException;
import com.infy.model.Appointment;
import com.infy.model.Doctor;
import com.infy.model.Patient;
import com.infy.model.Status;

public class HealthcareServiceImpl implements HealthcareService  {
	
	private HealthcareDAO dao;

	@Override
	public List<Doctor> showDoctors() throws HealthcareException {
		// will implement this method
		return null;
	}
	
	@Override
	public List<Doctor> fetchDoctor(String specialization) throws HealthcareException {
		// will implement this method
		return null;
	}
	
	@Override
	public Status bookAnAppointment(Patient patient, Appointment appointment) throws HealthcareException {
		// will implement this method
		return null;
	}
	
}

