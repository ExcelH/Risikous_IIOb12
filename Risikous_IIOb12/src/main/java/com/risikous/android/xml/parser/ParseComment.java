package com.risikous.android.xml.parser;


import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.comment.part.TimeStamp;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParseComment {

    public List<Comment> parseComment(String xml) {
        ParseXML2LIST p = new ParseXML2LIST();

        List<String> pubID = new LinkedList<>();
        List<String> author = new LinkedList<>();
        List<String> text = new LinkedList<>();
        List<String> timeStamp = new LinkedList<>();

        pubID = p.parseXML(xml, "ID");
        author = p.parseXML(xml, Author.class.getSimpleName().toLowerCase());
        text = p.parseXML(xml, Text.class.getSimpleName().toLowerCase());
        timeStamp = p.parseXML(xml, TimeStamp.class.getSimpleName().toLowerCase());

        List<Comment> comments = new LinkedList<>();
        for (int i = 0; i < author.size(); i++) {
            Comment comment = new Comment();
            comment.setPubID(new PubID(pubID.get(i)));
            comment.setAuthor(new Author(author.get(i)));
            comment.setText(new Text(text.get(i)));
            comment.setTimeStamp(new TimeStamp(timeStamp.get(i)));
            comments.add(comment);

        }

        return comments;
    }



}
