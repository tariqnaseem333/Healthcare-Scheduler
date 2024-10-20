package com.infy.userinterface;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infy.exception.HealthcareException;
import com.infy.model.Appointment;
import com.infy.model.Doctor;
import com.infy.model.Patient;
import com.infy.model.Status;
import com.infy.service.HealthcareService;
import com.infy.service.HealthcareServiceImpl;

public class HealthcareTester {
	
	private static HealthcareService healthcareService;
	private static PropertiesConfiguration propertiesConfiguration;
	private static final Log LOG = LogFactory.getLog(HealthcareTester.class);	
	
	public static void main(String[] args) throws ConfigurationException {
		healthcareService = new HealthcareServiceImpl(); 
		propertiesConfiguration = new Configurations().properties("configuration.properties");
		
		showDoctors();
		searchDoctor();
		bookAppointmentWithDoctor();		
		
	}
	
	public static void showDoctors() {
		LOG.info("Healthcare Appointment Scheduler | All Available Doctors");
		
		try {
			List<Doctor> doctors = healthcareService.showDoctors();
			
			LOG.info("--------------------------------------------------------------------------");
			LOG.info(String.format("%s %-5s %s %-8s %s %-15s %s %-15s %s %-15s %s", "|",
	                "Dr.ID", "|",  "Dr.Name", "|", "Specialization", "|",
	                "Available Date",  ":", "Time", "|"));
			
			for(Doctor doctor : doctors) {
				LOG.info(String.format("%s %-5s %s %-8s %s %-15s %s %-15s %s %-15s %s", "|",
		                doctor.getDoctorId(), "|", doctor.getDoctorName(), "|", doctor.getSpecialization(),
		                "|", doctor.getAvailableDate(), ":", doctor.getAvailableTime(), "|"));
			}
			
			LOG.info("--------------------------------------------------------------------------");
		} catch (HealthcareException exception) {
		    String exceptionMessage = exception.getMessage();
		    
		    if (exceptionMessage == null) {
		    	exceptionMessage = "Tester.GENERAL_EXCEPTION";
		    }

		    String errorMessage = (String)propertiesConfiguration.getProperty(exceptionMessage);
		    LOG.info(String.format("ERROR: %s", errorMessage));
		}		
		
	}
	
	public static void searchDoctor() {
		String specialization = "Dermatology";
		
		LOG.info("Healthcare Appointment Scheduler | Doctor by specialization: " + specialization);
		
		try {
			List<Doctor> doctors = healthcareService.fetchDoctor(specialization);
			
			LOG.info("--------------------------------------------------------------------------");
			LOG.info(String.format("%s %-5s %s %-8s %s %-15s %s %-15s %s %-15s %s", "|",
	                "Dr.ID", "|",  "Dr.Name", "|", "Specialization", "|",
	                "Available Date",  ":", "Time", "|"));
			
			for(Doctor doctor : doctors) {
				LOG.info(String.format("%s %-5s %s %-8s %s %-15s %s %-15s %s %-15s %s", "|",
		                doctor.getDoctorId(), "|", doctor.getDoctorName(), "|", doctor.getSpecialization(),
		                "|", doctor.getAvailableDate(), ":", doctor.getAvailableTime(), "|"));
			}
			
			LOG.info("--------------------------------------------------------------------------");
		} catch (HealthcareException exception) {
		    String exceptionMessage = exception.getMessage();
		    
		    if (exceptionMessage == null) {
		    	exceptionMessage = "Tester.GENERAL_EXCEPTION";
		    }

		    String errorMessage = (String)propertiesConfiguration.getProperty(exceptionMessage);
		    LOG.info(String.format("ERROR: %s", errorMessage));
		}	
		
	}
	
	public static void bookAppointmentWithDoctor() {
		LOG.info("Healthcare Appointment Scheduler | Book Apointment");
		
		Patient patient = new Patient(2001, "Sam", "Male", "High BP", 9898987656L, "10:00AM", LocalDate.of(2025, 11, 26));
		Appointment appointment = new Appointment(1001, 2001, "10:00AM", LocalDate.of(2025, 11, 26));
		
		try {
			Status status = healthcareService.bookAnAppointment(patient, appointment);

			LOG.info("--------------------------------------------------------------------------");
			
			if(status.equals(Status.CONFIRMED)) 
				LOG.info("Appointment Confirmed with Appointment Id: " + appointment.getAppointmentId() + 
						" for the Date " + appointment.getAppointmentDate() + " and Time " + appointment.getAppointmentTime());
			else if(status.equals(Status.NOT_CONFIRMED)) 
				LOG.info("Sorry!, The doctor you are trying to book is not avaiable for the date and time you provided.\n"
						+ "Please choose different doctor or different Time and Date.");
			
			LOG.info("--------------------------------------------------------------------------");
		} catch (HealthcareException exception) {
		    String exceptionMessage = exception.getMessage();
			
		    if (exceptionMessage == null) {
		    	exceptionMessage = "Tester.GENERAL_EXCEPTION";
		    }
		    
		    String errorMessage = (String) propertiesConfiguration.getProperty(exceptionMessage);
		    LOG.info(String.format("ERROR: %s", errorMessage));
		}	
		
	}
	
}


