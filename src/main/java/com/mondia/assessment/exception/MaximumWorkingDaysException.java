package com.mondia.assessment.exception;

public class MaximumWorkingDaysException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3102088460362896712L;

	public MaximumWorkingDaysException() {
		super("Exceeded maximum working days");
	}

	public MaximumWorkingDaysException(String message) {
		super(message);
	}
}
