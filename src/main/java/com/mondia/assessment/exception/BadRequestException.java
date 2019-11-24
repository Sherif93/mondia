package com.mondia.assessment.exception;

public class BadRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1417033974356172796L;

	public BadRequestException() {
		super("Bad Request");
	}

	public BadRequestException(String message) {
		super(message);
	}

}
