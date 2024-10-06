package com.infy.service;

import java.util.List;

import com.infy.exception.HealthcareException;
import com.infy.model.Appointment;
import com.infy.model.Doctor;
import com.infy.model.Patient;
import com.infy.model.Status;

public interface HealthcareService{
	
	public abstract List<Doctor> showDoctors() throws HealthcareException;
	
    /**
     * @params
     *         specialization - The doctor specialization
     * 
     * @operation Fetches the doctor list for the given specialization
     * 
     * @returns
     *          List<Doctor> - List of doctors fetched
     */
    public abstract List<Doctor> fetchDoctor(String specialization) throws HealthcareException;
    
	public abstract Status bookAnAppointment(Patient patient, Appointment appointment) throws HealthcareException;
	
}

