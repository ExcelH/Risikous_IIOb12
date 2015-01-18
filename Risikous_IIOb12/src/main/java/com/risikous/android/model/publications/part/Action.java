package com.risikous.android.model.publications.part;

import java.lang.String;

/**
 * Created by Excel on 16.01.2015.
 */
public class Action {
    private String name = "";

    public Action(String name) {
        this.name = name;
    }

    public Action() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
