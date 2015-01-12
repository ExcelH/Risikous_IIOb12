package com.risikous.android.model.comment.part;

/**
 * Created by Excel on 11.01.2015.
 */
public class Text {
    private String name = "";

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    private String tagName = "text";

    public Text() {

    }

    public Text(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
