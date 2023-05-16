package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidReturnDateException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
  Logger logger=LoggerFactory.getLogger(InvalidReturnDateException.class);
  logger.info("Invalid Return date!! Return date match a date in dd/mm/yyyy or dd-mm-yyyy format");
		}
	}

