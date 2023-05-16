package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidFromDistrictException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	 Logger logger=LoggerFactory.getLogger(InvalidFromDistrictException.class);
	 logger.info("Invalid From District !!  From District is only allowed for alphabets");
	}
}
