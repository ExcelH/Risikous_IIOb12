package com.risikous.android.model.publications.part;

import java.lang.String;

/**
 * Created by Excel on 16.01.2015.
 */
public class AvgRPZofReporter {
    private String name = "";

    public AvgRPZofReporter(String name) {
        this.name = name;
    }

    public AvgRPZofReporter() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
