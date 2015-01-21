package com.risikous.android.validation;

import java.io.File;
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

}

