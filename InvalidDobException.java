package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidDobException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static{
	Logger logger=LoggerFactory.getLogger(InvalidDobException.class);
	logger.info("Invalid dob!! dob match a date in dd/mm/yyyy or dd-mm-yyyy format");
	}
}
