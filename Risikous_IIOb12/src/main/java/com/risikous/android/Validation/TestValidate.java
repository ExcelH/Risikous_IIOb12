package com.risikous.android.Validation;

/**
 * Created by Excel on 03.01.2015.
 */
public class TestValidate {

    static String testString = "theggg";


    public static void main(String args[]) {

        ValidatorCollection test = new ValidatorCollection();
        if(test.validateQuantity(testString, 5)){System.out.println("success");}
            else {System.out.println("aborted");};

    }


}
