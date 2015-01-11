package com.risikous.android.parser;


import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParseSubComment {

    public List<Comment> parseComment(String xml) {
//        ParseXML2LIST pL = new ParseXML2LIST();
        ParseXML2STRING pS = new ParseXML2STRING();

//        String subXML = pS.parseSubXML(xml, ListOfAnswers.class.getSimpleName().toLowerCase());

 /*       List<String> author = new LinkedList<>();
        List<String> text = new LinkedList<>();
        List<String> timeStamp = new LinkedList<>();

        author = pL.parseXML(subXML, Author.class.getSimpleName().toLowerCase());
        text = pL.parseXML(xml, Text.class.getSimpleName().toLowerCase());
        timeStamp = pL.parseXML(xml, TimeStamp.class.getSimpleName().toLowerCase());

        List<Comment> subComments = new LinkedList<>();
        for (int i = 0; i < author.size(); i++) {
            Comment comment = new Comment();
            comment.setAuthor(new Author(author.get(i)));
            comment.setText(new Text(text.get(i)));
            comment.setTimeStamp(new TimeStamp(timeStamp.get(i)));
            subComments.add(comment);

        }
*/
        return pS.parseSubXML(xml, ListOfAnswers.class.getSimpleName().toLowerCase());
    }



}
