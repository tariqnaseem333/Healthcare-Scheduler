package com.infy.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;

import com.infy.dao.HealthcareDAO;
import com.infy.dao.HealthcareDAOImpl;
import com.infy.exception.HealthcareException;
import com.infy.model.Appointment;
import com.infy.model.Doctor;
import com.infy.model.Patient;
import com.infy.model.Status;
import com.infy.validator.Validator;

public class HealthcareServiceImpl implements HealthcareService  {
	
	private HealthcareDAO dao = new HealthcareDAOImpl();

	@Override
	public List<Doctor> showDoctors() throws HealthcareException {

		try {
			List<Doctor> doctorList = dao.getDoctorList();
			
			if(doctorList.isEmpty()) 				
				throw new HealthcareException("Service.NO_DOCTORS_FOUND");
			return doctorList;
		} catch (Exception exception) {
			if(exception.getMessage().contains("Service"))
				LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
			throw exception;
		}

	}
	
	@Override
	public List<Doctor> fetchDoctor(String specialization) throws HealthcareException {

		try {
			List<Doctor> doctorList = dao.findDoctors(specialization)
										 .stream()
										 .sorted((doctor1, doctor2) -> doctor1.getAvailableDate().compareTo(doctor2.getAvailableDate()))
										 .collect(Collectors.toList());
			if(doctorList.isEmpty())
				throw new HealthcareException("Service.DOCTOR_NOT_FOUND");
			return doctorList;
		} catch (Exception exception) {
			if(exception.getMessage().contains("Service"))
				LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
			throw exception;
		}

	}
	
	@Override
	public Status bookAnAppointment(Patient patient, Appointment appointment) throws HealthcareException {
		Validator validation = new Validator();
		
		try {
			validation.validatePatient(patient);
			
			Doctor doctor = dao.getDoctorList()
							   .stream()
							   .filter(doc->appointment.getDoctorId().equals(doc.getDoctorId()))
							   .findFirst()
							   .get();
			
			String[] availableTime = doctor.getAvailableTime().split("-");
			DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("hh:mm[a]").withLocale(Locale.US);
			LocalTime doctorStartTime = LocalTime.parse(availableTime[0], parseFormat);
			LocalTime doctorEndTime = LocalTime.parse(availableTime[1], parseFormat);
			
			LocalTime appointmentTime = LocalTime.parse(appointment.getAppointmentTime(), parseFormat);
			
			if(doctor.getAvailableDate().equals(appointment.getAppointmentDate())) {
				
				if(( appointmentTime.equals(doctorStartTime) || appointmentTime.isAfter(doctorStartTime) ) &&  
				   (  appointmentTime.equals(doctorEndTime) || appointmentTime.isBefore(doctorEndTime) )) {
					return dao.bookAppointment(appointment, patient);
				}
				
			}
			
			return Status.NOT_CONFIRMED;

		} catch (HealthcareException exception) {
			if(exception.getMessage().contains("Service"))
				LogFactory.getLog(getClass()).error(exception.getMessage(), exception);
			throw exception;
		}
		
	}
	
}


