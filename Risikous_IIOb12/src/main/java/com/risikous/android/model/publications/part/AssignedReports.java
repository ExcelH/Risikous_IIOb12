package com.risikous.android.model.publications.part;

import java.lang.String;

/**
 * Created by Excel on 16.01.2015.
 */
public class AssignedReports {
    private String name = "";

    public AssignedReports(String name) {
        this.name = name;
    }

    public AssignedReports() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
