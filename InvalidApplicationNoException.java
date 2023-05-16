package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidApplicationNoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
		Logger logger=LoggerFactory.getLogger(InvalidApplicationNoException.class);

	 logger.info("Invalid applicationNo!!!It doesn't have null values");
	}
}
