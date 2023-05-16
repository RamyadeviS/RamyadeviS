package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidFatherNameException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
	 Logger logger=LoggerFactory.getLogger(InvalidFatherNameException.class);
	 logger.info("Invalid Father name !! Father name is only allowed for alphabets");
	}

}
