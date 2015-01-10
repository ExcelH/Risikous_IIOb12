package com.risikous.android.model.questionnaire.part;

/**
 * Created by Excel on 10.01.2015.
 */
public class IncidentDescription {
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IncidentDescription() {

    }

    public IncidentDescription(String name) {
        this.name = name;
    }
}
