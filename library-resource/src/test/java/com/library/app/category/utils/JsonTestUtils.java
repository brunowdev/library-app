package com.library.app.category.utils;

import org.json.JSONException;
import org.junit.Ignore;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.InputStream;
import java.util.Scanner;

import static org.skyscreamer.jsonassert.JSONCompareMode.NON_EXTENSIBLE;

/**
 * Created by BRUNO-PC on 20/07/2017.
 */
@Ignore
public final class JsonTestUtils {

    public static final String BASE_JSON_DIR = "json/";

    private JsonTestUtils() {
    }

    public static String readJsonFile(final String relativePath) {
        final InputStream is = JsonTestUtils.class.getClassLoader().getResourceAsStream(BASE_JSON_DIR + relativePath);
        try (Scanner s = new Scanner(is)) {
            return s.useDelimiter("\\A").hasNext() ? s.next() : "";
        }
    }

    public static void assertJsonMatchesExpectedJson(final String actualJson, final String expectedJson) {
        try {
            JSONAssert.assertEquals(expectedJson, actualJson, NON_EXTENSIBLE);
        } catch (final JSONException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
