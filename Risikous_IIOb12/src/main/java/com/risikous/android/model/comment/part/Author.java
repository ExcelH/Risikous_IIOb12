package com.risikous.android.model.comment.part;

/**
 * Created by Excel on 11.01.2015.
 */
public class Author {
    private String name = "";
    private String tagName = "author";

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Author() {

    }

    public Author(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
