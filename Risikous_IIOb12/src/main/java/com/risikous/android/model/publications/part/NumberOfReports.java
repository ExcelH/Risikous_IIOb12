package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class NumberOfReports {
    private String name = "";

    public NumberOfReports() {
    }

    public NumberOfReports(String s) {
        this.name = s;
    }

    public String getNumberOfReports() {
        return name;
    }

    public void setNumberOfReports(String name) {
        this.name = name;
    }
}
