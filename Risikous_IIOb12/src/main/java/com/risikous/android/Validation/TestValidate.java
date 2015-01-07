package com.risikous.android.Validation;

import java.text.ParseException;

/**
 * Created by Excel on 03.01.2015.
 */
public class TestValidate {

    static String testString = "12:32";


    public static void main(String args[]) throws ParseException {

        ValidatorCollection test = new ValidatorCollection();
        if(test.validateTime(testString)){System.out.println("success");}
            else {System.out.println("aborted");};

    }


}
