package com.risikous.android.xml.builder;

/**
 * Created by Excel on 12.01.2015.
 */
public class BuildXMLTag {

    public String buildXMLTag(String Tag, String value) {

        java.lang.StringBuilder str = new java.lang.StringBuilder("<" + Tag + ">");
        str.append(value);
        str.append("</" + Tag + ">");

        return str.toString();
    }
}

