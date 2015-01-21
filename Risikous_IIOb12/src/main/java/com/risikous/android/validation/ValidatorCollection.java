package com.risikous.android.validation;


import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Excel on 03.01.2015.
 */

public class ValidatorCollection {

    public boolean validateChar(String s) {

        if (s == null || s.equals(""))
            return false;

        if (!s.matches("[a-zA-Z0-9._%+-]*"))
            return false;

        return true;
    }

    public boolean validateRequired(String s) {

        if (s.equals(""))
            return false;

        return true;
    }

    public boolean validateCountChar(String s, int max) {

        if (max < s.length())
            return false;

        return true;
    }

    public boolean validateDate(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date testDate = null;

        try {
            testDate = sdf.parse(s);
        } catch (ParseException e) {
            return false;
        }

        if (!sdf.format(testDate).equals(s))
            return false;

        return true;
    }

    public boolean validateTime(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date testTime = null;

        try {
            testTime = sdf.parse(s);
        } catch (ParseException e) {
            return false;
        }

        if (!sdf.format(testTime).equals(s))
            return false;

        return true;
    }

    public boolean validateFileSize(File file, double max) {
        double megabytes = 0;

        if (file.exists()) {

            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            megabytes = (kilobytes / 1024);
            System.out.println("Dateigröße:::"+megabytes+"MB");
        }

        return max >= megabytes;
    }

    public String encodeFileToBase64Binary(File file)
            throws IOException {

        byte[] bytes = loadFile(file);

        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();

        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

}

