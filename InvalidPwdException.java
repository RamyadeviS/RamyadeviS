package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidPwdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
   Logger logger=LoggerFactory.getLogger(InvalidPwdException.class);
   logger.info("Invalid password!! It should be greater than 8 characters,not null values");
}
}
