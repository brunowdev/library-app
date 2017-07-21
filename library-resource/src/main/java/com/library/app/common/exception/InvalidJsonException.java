package com.library.app.common.exception;

/**
 * Created by BRUNO-PC on 20/07/2017.
 */
public class InvalidJsonException extends RuntimeException {

	private static final long serialVersionUID = 0521727667537013505712L;

	public InvalidJsonException(final String message) {
		super(message);
	}

	public InvalidJsonException(final Throwable throwable) {
		super(throwable);
	}

}