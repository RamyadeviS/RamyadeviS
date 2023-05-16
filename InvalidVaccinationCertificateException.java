package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidVaccinationCertificateException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	Logger logger=LoggerFactory.getLogger(InvalidVaccinationCertificateException.class);
	logger.info("Vaccination certificate need compulsory!! E-pass cannot be filled without certificate");
	}
}
