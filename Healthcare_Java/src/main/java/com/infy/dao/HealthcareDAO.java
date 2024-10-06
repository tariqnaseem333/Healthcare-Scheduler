package com.infy.dao;

import java.util.List;

import com.infy.model.Appointment;
import com.infy.model.Doctor;
import com.infy.model.Patient;
import com.infy.model.Status;

public interface HealthcareDAO {
	
	public abstract List<Doctor> getDoctorList();
	
    /**
     * @params
     *         specialization - The doctor specialization
     * 
     * @operation Fetches the doctors list for the given specialization
     * 
     * @returns
     *          List<Doctor> - List of doctors fetched
     */
	public abstract List<Doctor> findDoctors(String Specialization);
	
    /**
     * @params
     *         doctor - The doctor that has to be booked
     * 		   patient - The patient who is booking appointment
     * 
     * @operation Checks if there is no appointment with this doctor and books appointment
     * 
     * @returns
     *          Appointment - Appointment that is booked 
     */
	public abstract Status bookAppointment(Appointment appointment, Patient patient);
	
}
