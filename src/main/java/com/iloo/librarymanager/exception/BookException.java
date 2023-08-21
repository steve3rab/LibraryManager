package com.iloo.librarymanager.exception;

/**
 * Custom exception class for book-related errors.
 */
public class BookException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 3867541565459781596L;

	public BookException(String message) {
		super(message);
	}
}
