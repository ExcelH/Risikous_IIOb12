package com.risikous.android.xml.parser;


import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.*;
import com.risikous.android.sqlite.SQLiteHelper_SubComment;

import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParseSubComment {

    public void parseComment(String xml, SQLiteHelper_SubComment db, String publicationID) {
        ParseXML2LIST_SubComment pS = new ParseXML2LIST_SubComment();

        List<Comment> subComments = pS.parseSubXML(xml, ListOfAnswers.class.getSimpleName().toLowerCase());

        for(int i=0; i<subComments.size(); i++){
            Comment comment = new Comment();
            comment.setAuthorDB(subComments.get(i).getAuthor().getName());
            comment.setComIDDB(subComments.get(i).getComID().getName());
            comment.setTextDB(subComments.get(i).getText().getName());
            comment.setTimeStampDB(subComments.get(i).getTimeStamp().getName());
            comment.setPubIDDB(publicationID);

            if (comment.getTextDB() != null)
                db.addSubComment(comment);

        }

    }


}
