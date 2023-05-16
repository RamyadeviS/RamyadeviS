package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidGenderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
   Logger logger=LoggerFactory.getLogger(InvalidGenderException.class);
   logger.info("Mismatch gender!!");
	}
}
