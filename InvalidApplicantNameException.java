package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidApplicantNameException extends Exception {
	
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static{
		Logger logger=LoggerFactory.getLogger(InvalidApplicantNameException.class);
		logger.info("Invalid applicant name!! applicant name is only allowed for alphabets");	
		
	}
}
