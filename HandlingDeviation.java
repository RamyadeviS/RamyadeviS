package com.chainsys.epassproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class HandlingDeviation {
    @ExceptionHandler({ Exception.class, RuntimeException.class })
    
    //@ExceptionHandler
    public ModelAndView handleExceptions(Exception ex) {
    	Logger logger = LoggerFactory.getLogger(HandlingDeviation.class);
    	logger.info(ex.getLocalizedMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("ErrorMessage", "An unexpected error occurred.");
        view.setViewName("ErrorPopup");
        return view;
    }
}
