package com.risikous.android.xml.builder;

/**
 * Created by Excel on 12.01.2015.
 */
public class StringBuilder {

    public String connectString(String firstString, String lastString) {

        java.lang.StringBuilder str = new java.lang.StringBuilder(firstString);
        str.append(lastString);

        return str.toString();
    }
}
