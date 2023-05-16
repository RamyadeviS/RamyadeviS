package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidToDistrictException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	Logger logger=LoggerFactory.getLogger(InvalidToDistrictException.class);
	logger.info("Invalid To District !!  T District is only allowed for alphabets");	
	}
	
}
