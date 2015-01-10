package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class PubID {
    private String name = "";

    public PubID(){}

    @Override
    public String toString() {
        return "PubID{" +
                "name='" + name + '\'' +
                '}';
    }

    public PubID(String s) {
        this.name = s;
    }

    public void setPubID(String name) {
        this.name = name;
    }

    public String getPubID() {
        return name;
    }
}
