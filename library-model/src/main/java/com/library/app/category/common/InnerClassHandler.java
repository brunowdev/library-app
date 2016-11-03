package com.library.app.category.common;

import com.google.common.reflect.TypeToken;

/**
 * This class help to handle with Generic types in subclasses
 *
 * @author BRUNO-PC
 *
 * @param <T>
 */
@SuppressWarnings({ "unchecked", "serial" })
public abstract class InnerClassHandler<T> {

	private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
	};

	protected final Class<T> type = (Class<T>) typeToken.getType();

}
