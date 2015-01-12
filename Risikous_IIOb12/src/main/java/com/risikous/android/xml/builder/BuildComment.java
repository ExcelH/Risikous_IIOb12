package com.risikous.android.xml.builder;

import com.risikous.android.model.comment.Comment;


/**
 * Created by Excel on 12.01.2015.
 */
public class BuildComment {

    public String buildComment(Comment comment) {

        String commentString = "";
        BuildXMLTag build = new BuildXMLTag();
        StringBuilder sB = new StringBuilder();

        commentString = sB.connectString(commentString,build.buildXMLTag(comment.getPubID().getTagName(),comment.getPubID().getName()));
        commentString = sB.connectString(commentString,build.buildXMLTag(comment.getAuthor().getTagName(),comment.getAuthor().getName()));
        commentString = sB.connectString(commentString, build.buildXMLTag(comment.getText().getTagName(), comment.getText().getName()));

        commentString = build.buildXMLTag(comment.getTagName(),commentString);

        System.out.println("StringComment:::::::::"+commentString);
        return commentString;
    }
}
