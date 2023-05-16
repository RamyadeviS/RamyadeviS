package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidMobileNoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	  Logger logger=LoggerFactory.getLogger(InvalidMobileNoException.class);
	  logger.info("Invalid mobileNo!!It must be contain 10 digits and allowed only numeric values");
	}
}
