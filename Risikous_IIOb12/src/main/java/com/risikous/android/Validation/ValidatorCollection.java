package com.risikous.android.Validation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Excel on 03.01.2015.
 */

public class ValidatorCollection {

    public boolean validateChar(String s) {
        s = s.trim();

        if (s == null || s.equals(""))
            return false;

        if (!s.matches("[a-zA-Z0-9._%+-]*"))
            return false;

        return true;
    }

    public boolean validateRequired(String s) {
        s = s.trim();

        if (!s.matches("true"))
            return false;

        return true;
    }

    public boolean validateCountChar(String s, int max) {
        s = s.trim();

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

    public boolean validateFileSize(File file, int max) {
        double megabytes = 0;

        if (file.exists()) {

            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            megabytes = (kilobytes / 1024);
        }

        if (max < megabytes)
            return true;

        return false;
    }

    public boolean validateFileSize(File file, String s) throws IOException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        String detectedEncode = null;

        fis = new FileInputStream(file);
        isr = new InputStreamReader(fis);

        s = isr.getEncoding();

        if (fis != null)
            fis.close();
        if (isr != null)
            isr.close();

        if(s == detectedEncode)
            return true;

        return false;

    }

}

