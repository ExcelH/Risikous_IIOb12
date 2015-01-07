package com.risikous.android.Validation;

/**
 * Created by Excel on 03.01.2015.
 */

public class ValidatorCollection {

    public boolean validateChar(String testString) {
        testString = testString.trim();

        if (testString == null || testString.equals(""))
            return false;

        if (!testString.matches("[a-zA-Z0-9._%+-]*"))
            return false;

        return true;
    }

    public boolean validateRequired(String testString) {
        testString = testString.trim();

        if (!testString.matches("true"))
            return false;

        return true;
    }

    public boolean validateCountChar(String testString, int max) {
        testString = testString.trim();

        if(max < testString.length())
            return false;

        return true;
    }

}
