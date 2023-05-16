package com.chainsys.epassproject.myexception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicationOfMailIdException extends Exception {

	private static final long serialVersionUID = 1L;

	static {
		Logger logger = LoggerFactory.getLogger(DuplicationOfMailIdException.class);
		logger.info("This Mail id is Already Exist use another mail id");
	}
}