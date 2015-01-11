package com.risikous.android.model.comment;

import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.comment.part.TimeStamp;

/**
 * Created by Excel on 11.01.2015.
 */
public class Comment {
    private Author author;
    private PubID pubID;
    private Text text;
    private TimeStamp timeStamp;

    public Comment() {
    }

    public Comment(Author author, PubID pubID, Text text, TimeStamp timeStamp) {
        this.author = author;
        this.pubID = pubID;
        this.text = text;
        this.timeStamp = timeStamp;
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
}
