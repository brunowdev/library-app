package com.library.app.common.exception;

/**
 * Created by BRUNO-PC on 20/07/2017.
 */
public class InvalidJsonException extends RuntimeException {

	private static final long serialVersionUID = 0b101010001111010111110110111101011111000001011101000101111001010L;

	public InvalidJsonException(final String message) {
		super(message);
	}

	public InvalidJsonException(final Throwable throwable) {
		super(throwable);
	}

}