package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidTicketNoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
		Logger logger=LoggerFactory.getLogger(InvalidTicketNoException.class);
		logger.info("Invalid ticket no!!! Only upper case and numbers are allowed");
	}

}
