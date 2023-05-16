package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExistingMailIdException extends Exception {

	private static final long serialVersionUID = 1L;

	static {
		Logger logger = LoggerFactory.getLogger(ExistingMailIdException.class);

		logger.info("Invalid mailId !! this mail id already existing");
	}
}
