package com.eProfile.eProfileExperts.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDateFormatter {
    private static final String DEFAULT_FILE_PATTERN = "yyyy-MM-dd";

    public static String createFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FILE_PATTERN);
        return format.format(date);
    }//  w w
}
