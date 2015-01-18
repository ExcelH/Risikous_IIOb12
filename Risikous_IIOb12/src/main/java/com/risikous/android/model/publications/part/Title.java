package com.risikous.android.model.publications.part;

/**
 * Created by Excel on 09.01.2015.
 */
public class Title {
    private String name = "";

    public Title() {
    }

    public Title(String s) {
        this.name = s;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }
}
