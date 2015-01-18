package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class RevisionDate {
    private String name = "";

    public RevisionDate() {
    }

    public RevisionDate(String s) {
        this.name = s;
    }

    public String getRevisionDate() {
        return name;
    }

    public void setRevisionDate(String name) {
        this.name = name;
    }
}
