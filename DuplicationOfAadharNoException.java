package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicationOfAadharNoException extends Exception {

	private static final long serialVersionUID = 1L;

	static {
		Logger logger = LoggerFactory.getLogger(DuplicationOfAadharNoException.class);
		logger.info("This Aadhar number is Already Exist use another Aadhar number");

	}
}
