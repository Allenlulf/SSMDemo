package com.epoint.exception;

/**
 * 预约业务异常
 * 
 * @author lulf
 *
 */
public class AppointException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppointException(String message) {
		super(message);
	}

	public AppointException(String message, Throwable cause) {
		super(message, cause);
	}

}
