package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidMailIdException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	 Logger logger=LoggerFactory.getLogger(InvalidMailIdException.class);
	 logger.info("Invalid mail id!! it should contain only alphabets after @ symbol");
	}
}
