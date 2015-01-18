package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class NumberOfComments {
    private String name = "";

    public NumberOfComments() {
    }

    public NumberOfComments(String s) {
        this.name = s;
    }

    public String getNumberOfComments() {
        return name;
    }

    public void setNumberOfComments(String name) {
        this.name = name;
    }
}
