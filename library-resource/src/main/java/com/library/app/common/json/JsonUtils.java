package com.library.app.common.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by BRUNO-PC on 20/07/2017.
 */
public final class JsonUtils {

	private JsonUtils() {
	}

	public static JsonElement getJsonElementWithId(final Long id) {
		final JsonObject idJson = new JsonObject();
		idJson.addProperty("id", id);

		return idJson;
	}

}