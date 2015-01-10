package com.risikous.android.model.questionnaire.part;

/**
 * Created by Excel on 10.01.2015.
 */
public class ContactInformation {
    private String name = "";

    public ContactInformation(String name) {
        this.name = name;
    }

    public ContactInformation() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
