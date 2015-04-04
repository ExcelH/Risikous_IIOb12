package com.risikous.android.xml.parser;


import android.os.AsyncTask;

import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.comment.part.TimeStamp;
import com.risikous.android.model.publications.part.Action;
import com.risikous.android.model.publications.part.AssignedReports;
import com.risikous.android.model.publications.part.AvgRPZofQMB;
import com.risikous.android.model.publications.part.AvgRPZofReporter;
import com.risikous.android.model.publications.part.Category;
import com.risikous.android.model.publications.part.IncidentReport;
import com.risikous.android.model.publications.part.MaxRPZofQMB;
import com.risikous.android.model.publications.part.MaxRPZofReporter;
import com.risikous.android.model.publications.part.MinRPZofQMB;
import com.risikous.android.model.publications.part.MinRPZofReporter;
import com.risikous.android.model.publications.part.Title;
import com.risikous.android.request.GetRequest;
import com.risikous.android.sqlite.SQLiteHelper_Comment;
import com.risikous.android.url_uri.Constants;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParseComment {

    public void parseComment(String xml, SQLiteHelper_Comment db) {
        ParseXML2LIST p = new ParseXML2LIST();

        List<String> pubID;
        List<String> author;
        List<String> text;
        List<String> timeStamp;

        pubID = p.parseXML(xml, "ID");
        author = p.parseXML(xml, Author.class.getSimpleName().toLowerCase());
        text = p.parseXML(xml, Text.class.getSimpleName().toLowerCase());
        timeStamp = p.parseXML(xml, TimeStamp.class.getSimpleName().toLowerCase());

        for (int i = 0; i < author.size(); i++) {
            Comment comment = new Comment();
            comment.setPubIDDB(pubID.get(i));
            System.out.println(" Comment ID "+pubID.get(i));
            comment.setAuthorDB(author.get(i));
            comment.setTextDB(text.get(i));
            comment.setTimeStampDB(timeStamp.get(i));

            if (comment.getPubIDDB() != null)
                db.addComment(comment);

        }
    }
}
