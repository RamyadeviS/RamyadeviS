package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidSourceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{

	Logger logger=LoggerFactory.getLogger(InvalidSourceException.class);
	logger.info("Invalid data!! Only allowed the alphabets");
	}
}
