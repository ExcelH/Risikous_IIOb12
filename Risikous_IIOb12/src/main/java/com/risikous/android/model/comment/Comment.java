package com.risikous.android.model.comment;

import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.ComID;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.comment.part.TimeStamp;

/**
 * Created by Excel on 11.01.2015.
 */
public class Comment {
    private Author author;
    private String authorDB;
    private PubID pubID;
    private String pubIDDB;
    private ComID comID;
    private String comIDDB;
    private Text text;
    private String textDB;
    private TimeStamp timeStamp;
    private String timeStampDB;
    private String tagName = "comment";
    private int ID;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Comment() {
    }

    public Comment(Author author, PubID pubID, ComID comID, Text text, TimeStamp timeStamp) {
        this.author = author;
        this.pubID = pubID;
        this.comID = comID;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    public ComID getComID() {
        return comID;
    }

    public void setComID(ComID comID) {
        this.comID = comID;
    }

    public String getComIDDB() {
        return comIDDB;
    }

    public void setComIDDB(String comIDDB) {
        this.comIDDB = comIDDB;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public PubID getPubID() {
        return pubID;
    }

    public void setPubID(PubID pubID) {
        this.pubID = pubID;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAuthorDB() {
        return authorDB;
    }

    public void setAuthorDB(String authorDB) {
        this.authorDB = authorDB;
    }

    public String getPubIDDB() {
        return pubIDDB;
    }

    public void setPubIDDB(String pubIDDB) {
        this.pubIDDB = pubIDDB;
    }

    public String getTextDB() {
        return textDB;
    }

    public void setTextDB(String textDB) {
        this.textDB = textDB;
    }

    public String getTimeStampDB() {
        return timeStampDB;
    }

    public void setTimeStampDB(String timeStampDB) {
        this.timeStampDB = timeStampDB;
    }
}
