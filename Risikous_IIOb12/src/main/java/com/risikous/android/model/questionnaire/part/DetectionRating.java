package com.risikous.android.model.questionnaire.part;

/**
 * Created by Excel on 10.01.2015.
 */
public class DetectionRating {
    private String name = "";
    private String tagName = "detectionRating";

    public DetectionRating(String name, String tagName) {
        this.name = name;
        this.tagName = tagName;
    }

    public String getTagName() {

        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public DetectionRating() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DetectionRating(String name) {

        this.name = name;
    }
}
