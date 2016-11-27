package com.library.app.common.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class FieldNotValidException extends RuntimeException {

	private static final long serialVersionUID = 6938668678983684689L;

	private final String fieldName;

	public FieldNotValidException(final String fieldName, final String message) {
		super(message);
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	@Override
	public String toString() {
		return "FieldNotValidException [fieldName=" + fieldName + "]";
	}

}
