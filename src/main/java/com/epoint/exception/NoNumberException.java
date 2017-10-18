package com.epoint.exception;

/**
 * ¿â´æ²»×ãÒì³£
 * 
 * @author lulf
 *
 */
public class NoNumberException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoNumberException(String message) {
		super(message);
	}

	public NoNumberException(String message, Throwable cause) {
		super(message, cause);
	}
}
