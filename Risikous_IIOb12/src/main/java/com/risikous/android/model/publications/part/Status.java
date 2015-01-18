package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class Status {
    private String name = "";

    public Status() {
    }

    public Status(String s) {
        this.name = s;
    }

    public String getStatus() {
        return name;
    }

    public void setStatus(String name) {
        this.name = name;
    }
}
