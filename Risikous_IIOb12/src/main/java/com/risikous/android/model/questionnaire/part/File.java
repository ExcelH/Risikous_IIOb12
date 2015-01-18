package com.risikous.android.model.questionnaire.part;

/**
 * Created by Excel on 10.01.2015.
 */
public class File {
    private String name = "";
    private String tagName = "file";

    public File(String name, String tagName, java.io.File file) {
        this.name = name;
        this.tagName = tagName;
        this.file = file;
    }

    public java.io.File getFile() {
        return file;
    }

    public void setFile(java.io.File file) {
        this.file = file;
    }

    private java.io.File file = null;

    public File(String name, String tagName) {
        this.name = name;
        this.tagName = tagName;
    }

    public String getTagName() {

        return tagName;
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

    public File() {

    }

    public File(String name) {
        this.name = name;
    }
}
