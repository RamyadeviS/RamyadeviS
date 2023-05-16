package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidAadharNoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
		Logger logger=LoggerFactory.getLogger(InvalidAadharNoException.class);	
	    logger.info("Invalid aadhar no!!It must be contain 12 digits and allowed only numeric values");
	}
	}


