package com.risikous.android.xml.parser;


import android.app.Activity;
import android.os.AsyncTask;

import com.risikous.android.model.publications.Publication;
import com.risikous.android.model.publications.part.*;
import com.risikous.android.request.GetRequest;
import com.risikous.android.sqlite.SQLiteHelper_Comment;
import com.risikous.android.sqlite.SQLiteHelper_Publication;
import com.risikous.android.sqlite.SQLiteHelper_SubComment;
import com.risikous.android.url_uri.Constants;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParsePublication {


    Activity activity;
    SQLiteHelper_Comment commentDB;
    SQLiteHelper_SubComment subCommentDB;

    public ParsePublication(Activity activity) {
        this.activity = activity;
    }

    public void parsePublication(final String xml, final SQLiteHelper_Publication db) {
        final ParseXML2LIST p = new ParseXML2LIST();

        commentDB = new SQLiteHelper_Comment(activity);
        subCommentDB = new SQLiteHelper_SubComment(activity);

        for(int i = 0; i < commentDB.getAllComments().size(); i++){
            commentDB.deleteComment(commentDB.getAllComments().get(i));
        }

        for(int i = 0; i < subCommentDB.getAllSubComments().size(); i++){
            subCommentDB.deleteSubComment(subCommentDB.getAllSubComments().get(i));
        }

        final List<String> pubID;
        List<String> entryDate;
        List<String> revisionDate;
        List<String> title;
        List<String> status;
        List<String> numberOfReports;
        List<String> numberOfComments;

        pubID = p.parseXML(xml, "ID");
        entryDate = p.parseXML(xml, EntryDate.class.getSimpleName().toLowerCase());
        revisionDate = p.parseXML(xml, RevisionDate.class.getSimpleName().toLowerCase());
        title = p.parseXML(xml, Title.class.getSimpleName().toLowerCase());
        status = p.parseXML(xml, Status.class.getSimpleName().toLowerCase());
        numberOfReports = p.parseXML(xml, NumberOfReports.class.getSimpleName().toLowerCase());
        numberOfComments = p.parseXML(xml, NumberOfComments.class.getSimpleName().toLowerCase());


        for (int i = 0; i < pubID.size(); i++) {
            final Publication publication = new Publication();
            final String publicationID = pubID.get(i);
            if (pubID.get(i) != null) publication.setPubIDDB(pubID.get(i));
            if (entryDate.get(i) != null) publication.setEntryDateDB(entryDate.get(i));
            if (revisionDate.get(i) != null) publication.setRevisionDateDB(revisionDate.get(i));
            if (title.get(i) != null) publication.setTitleDB(title.get(i));
            if (status.get(i) != null) publication.setStatusDB(status.get(i));
            if (numberOfReports.get(i) != null)
                publication.setNumberOfReportsDB(numberOfReports.get(i));
            if (numberOfComments.get(i) != null)
                publication.setNumberOfCommentsDB(numberOfComments.get(i));

            String completedXML_URL = Constants.PUBLICATION_GET_FULL_PUB_URL(pubID.get(i));

            new GET(new ResponseCallback() {
                @Override
                public void onResponse(String completedXML) {

                    ParseXML2LIST p = new ParseXML2LIST();
                    List<String> newTitle;
                    List<String> incidentReporter;
                    List<String> minRPZofReporter;
                    List<String> avgRPZofReporter;
                    List<String> maxRPZofReporter;
                    List<String> minRPZofQMB;
                    List<String> avgRPZofQMB;
                    List<String> maxRPZofQMB;
                    List<String> category;
                    List<String> action;
                    List<String> assignedReports;

                    newTitle = p.parseXML(completedXML, Title.class.getSimpleName().toLowerCase());
                    incidentReporter = p.parseXML(completedXML, IncidentReport.class.getSimpleName().toLowerCase());
                    minRPZofReporter = p.parseXML(completedXML, MinRPZofReporter.class.getSimpleName().toLowerCase());
                    avgRPZofReporter = p.parseXML(completedXML, AvgRPZofReporter.class.getSimpleName().toLowerCase());
                    maxRPZofReporter = p.parseXML(completedXML, MaxRPZofReporter.class.getSimpleName().toLowerCase());
                    minRPZofQMB = p.parseXML(completedXML, MinRPZofQMB.class.getSimpleName().toLowerCase());
                    avgRPZofQMB = p.parseXML(completedXML, AvgRPZofQMB.class.getSimpleName().toLowerCase());
                    maxRPZofQMB = p.parseXML(completedXML, MaxRPZofQMB.class.getSimpleName().toLowerCase());
                    category = p.parseXML(completedXML, Category.class.getSimpleName().toLowerCase());
                    action = p.parseXML(completedXML, Action.class.getSimpleName().toLowerCase());
                    assignedReports = p.parseXML(completedXML, AssignedReports.class.getSimpleName().toLowerCase());

                    for (int j = 0; j < newTitle.size(); j++) {

                        String noEntry = "Kein Eintrag";
                        if (!newTitle.isEmpty()) publication.setTitleDB(newTitle.get(j));
                        else publication.setTitleDB(noEntry);
                        if (incidentReporter.get(j) != null) {
                            publication.setIncidentReportDB(incidentReporter.get(j));
                        } else publication.setIncidentReportDB(noEntry);
                        if (!minRPZofReporter.isEmpty())
                            publication.setMinRPZofReporterDB(minRPZofReporter.get(j));
                        else publication.setMinRPZofReporterDB(noEntry);
                        if (!avgRPZofReporter.isEmpty())
                            publication.setAvgRPZofReporterDB(avgRPZofReporter.get(j));
                        else publication.setAvgRPZofReporterDB(noEntry);
                        if (!maxRPZofReporter.isEmpty())
                            publication.setMaxRPZofReporterDB(maxRPZofReporter.get(j));
                        else publication.setMaxRPZofReporterDB(noEntry);
                        if (!minRPZofQMB.isEmpty())
                            publication.setMinRPZofQMBDB(minRPZofQMB.get(j));
                        else publication.setMinRPZofQMBDB(noEntry);
                        if (!avgRPZofQMB.isEmpty())
                            publication.setAvgRPZofQMBDB(avgRPZofQMB.get(j));
                        else publication.setAvgRPZofQMBDB(noEntry);
                        if (!maxRPZofQMB.isEmpty())
                            publication.setMaxRPZofQMBDB(maxRPZofQMB.get(j));
                        else publication.setMaxRPZofQMBDB(noEntry);
                        if (!category.isEmpty()) publication.setCategoryDB(category.get(j));
                        else publication.setCategoryDB(noEntry);
                        if (!action.isEmpty()) publication.setActionDB(action.get(j));
                        else publication.setActionDB(noEntry);
                        if (!assignedReports.isEmpty())
                            publication.setAssignedReportsDB(assignedReports.get(j));
                        else publication.setAssignedReportsDB(noEntry);

                        if (!db.verification(publication.getPubIDDB())) {
                            db.addPublication(publication);
                        } else db.updatePublication(publication);
                    }
                }
            }, completedXML_URL).execute();

            final int finalI = i;
            new GET(new ResponseCallback() {
                @Override
                public void onResponse(String commentXML) {

                    GetComment(commentXML, publicationID);
                    GetSubComment(commentXML, publicationID);

                }
            }, Constants.COMMENT_GET_URL(pubID.get(i))).execute();
            commentDB.close();
            subCommentDB.close();
        }
    }

    public class GET extends AsyncTask<Void, Void, String> {


        private String data = null;
        private ResponseCallback responseCallback;
        private String url = null;


        public GET(ResponseCallback callback, String url) {
            this.url = url;
            responseCallback = callback;
        }

        @Override
        protected String doInBackground(Void... arg0) {
            GetRequest gr = new GetRequest();
            return gr.GetXML(url);
        }

        @Override
        protected void onPostExecute(String result) {
            if (responseCallback != null)
                responseCallback.onResponse(result);

        }
    }

    private interface ResponseCallback {
        public void onResponse(String s);

    }

    private void GetComment(String xml, String pubID) {
        ParseComment p = new ParseComment();
        p.parseComment(xml, commentDB, pubID);

    }

    private void GetSubComment(String xml, String pubID) {
        ParseSubComment p = new ParseSubComment();
        p.parseComment(xml, subCommentDB, pubID);

    }
}

