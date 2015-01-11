package com.risikous.android.parser;


import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.*;

import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParseSubComment {

    public List<Comment> parseComment(String xml) {
        ParseXML2LIST_SubComment pS = new ParseXML2LIST_SubComment();

        return pS.parseSubXML(xml, ListOfAnswers.class.getSimpleName().toLowerCase());
    }


}
