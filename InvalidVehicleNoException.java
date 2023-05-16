package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidVehicleNoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	Logger logger=LoggerFactory.getLogger(InvalidVehicleNoException.class);
	logger.info("Invalid vehicles no!!! Only upper case and numbers are allowed");
		}

	}

