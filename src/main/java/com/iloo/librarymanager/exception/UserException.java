package com.iloo.librarymanager.exception;

/**
 * Custom exception class for user-related errors.
 */
public class UserException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1580907428094519203L;

	public UserException(String message) {
		super(message);
	}
}
