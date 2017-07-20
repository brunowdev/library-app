package com.library.app.category.utils;

import org.junit.Ignore;

/**
 * Created by BRUNO-PC on 20/07/2017.
 */
@Ignore
public class TestFileUtils {

    private static final String PATH_REQUEST = "/request/";
    private static final String PATH_RESPONSE = "/response/";

    private TestFileUtils() {
    }

    public static String getPathFileRequest(final String mainFolder, final String fileName) {
        return mainFolder.concat(PATH_REQUEST).concat(fileName);
    }

    public static String getPathFileResponse(final String mainFolder, final String fileName) {
        return mainFolder.concat(PATH_RESPONSE).concat(fileName);
    }

}