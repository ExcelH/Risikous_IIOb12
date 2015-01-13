package com.risikous.android.model.questionnaire.part;

/**
 * Created by Excel on 10.01.2015.
 */
public class IncidentDescription {
    private String name = "";
    private String tagName = "incidentDescription";

    public String getName() {
        return name;
    }

    public IncidentDescription(String name, String tagName) {
        this.name = name;
        this.tagName = tagName;
    }

    public String getTagName() {

        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
