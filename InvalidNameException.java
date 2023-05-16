package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidNameException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	Logger logger=LoggerFactory.getLogger(InvalidNameException.class);
	logger.info("Invalid name!! Only allowed for alphabets");	
	}
}
