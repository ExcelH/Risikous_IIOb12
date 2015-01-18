package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class PubID {
    private String name = "";

    public PubID() {
    }

    public PubID(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return "PubID{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getPubID() {
        return name;
    }

    public void setPubID(String name) {
        this.name = name;
    }
}
