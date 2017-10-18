package com.epoint.exception;

/**
 * ÷ÿ∏¥‘§‘º“Ï≥£
 * 
 * @author lulf
 *
 */
public class RepeatAppointException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatAppointException(String message) {
		super(message);
	}

	public RepeatAppointException(String message, Throwable cause) {
		super(message, cause);
	}

}
