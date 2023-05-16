package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidNoOfPassengers extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	Logger logger=LoggerFactory.getLogger(InvalidNoOfPassengers.class);
    logger.info("Invalid passengers!! passengers must be 5 members only allowed ");
	}
	
}
