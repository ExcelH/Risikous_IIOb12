package com.risikous.android.model.questionnaire.part;

/**
 * Created by Excel on 10.01.2015.
 */
public class ImmediateMeasure {
    private String name = "";
    private String tagName = "immediateMeasure";

    public String getTagName() {
        return tagName;
    }

    public ImmediateMeasure(String name, String tagName) {
        this.name = name;
        this.tagName = tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImmediateMeasure() {

    }

    public ImmediateMeasure(String name) {
        this.name = name;
    }
}
