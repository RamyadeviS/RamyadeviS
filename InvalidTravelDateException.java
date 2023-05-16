package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidTravelDateException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
		Logger logger=LoggerFactory.getLogger(InvalidTravelDateException.class);
		
		logger.info("Invalid travel date!! travel date match a date in dd/mm/yyyy or dd-mm-yyyy format");
	}
}
