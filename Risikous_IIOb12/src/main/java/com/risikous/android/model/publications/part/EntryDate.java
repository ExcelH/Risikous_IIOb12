package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class EntryDate {
    private String name = "";

    public EntryDate() {

    }

    public EntryDate(String s) {
        this.name = s;
    }

    public String getEntryDate() {
        return name;
    }

    public void setEntryDate(String name) {
        this.name = name;
    }


}
