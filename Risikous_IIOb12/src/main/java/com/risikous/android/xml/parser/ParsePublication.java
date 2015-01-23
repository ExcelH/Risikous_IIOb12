package com.risikous.android.xml.parser;


import com.risikous.android.model.publications.Publication;
import com.risikous.android.model.publications.part.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParsePublication {

    public Publication parseCompletedPublication(String xml, Publication publication) {
        ParseXML2LIST p = new ParseXML2LIST();

        String noEntry = "Kein Eintrag";

        List<String> title;
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

        title = p.parseXML(xml, Title.class.getSimpleName().toLowerCase());
        incidentReporter = p.parseXML(xml, IncidentReport.class.getSimpleName().toLowerCase());
        minRPZofReporter = p.parseXML(xml, MinRPZofReporter.class.getSimpleName().toLowerCase());
        avgRPZofReporter = p.parseXML(xml, AvgRPZofReporter.class.getSimpleName().toLowerCase());
        maxRPZofReporter = p.parseXML(xml, MaxRPZofReporter.class.getSimpleName().toLowerCase());
        minRPZofQMB = p.parseXML(xml, MinRPZofQMB.class.getSimpleName().toLowerCase());
        avgRPZofQMB = p.parseXML(xml, AvgRPZofQMB.class.getSimpleName().toLowerCase());
        maxRPZofQMB = p.parseXML(xml, MaxRPZofQMB.class.getSimpleName().toLowerCase());
        category = p.parseXML(xml, Category.class.getSimpleName().toLowerCase());
        action = p.parseXML(xml, Action.class.getSimpleName().toLowerCase());
        assignedReports = p.parseXML(xml, AssignedReports.class.getSimpleName().toLowerCase());

        for (int i = 0; i < title.size(); i++) {

            if(!title.isEmpty()) publication.setTitle(new Title(title.get(i)));
            else publication.setTitle(new Title(noEntry));
            if(!incidentReporter.isEmpty()) publication.setIncidentReport(new IncidentReport(incidentReporter.get(i)));
            else publication.setIncidentReport(new IncidentReport(noEntry));
            if(!minRPZofReporter.isEmpty()) publication.setMinRPZofReporter(new MinRPZofReporter(minRPZofReporter.get(i)));
            else publication.setMinRPZofReporter(new MinRPZofReporter(noEntry));
            if(!avgRPZofReporter.isEmpty()) publication.setAvgRPZofReporter(new AvgRPZofReporter(avgRPZofReporter.get(i)));
            else publication.setAvgRPZofReporter(new AvgRPZofReporter(noEntry));
            if(!maxRPZofReporter.isEmpty()) publication.setMaxRPZofReporter(new MaxRPZofReporter(maxRPZofReporter.get(i)));
            else publication.setMaxRPZofReporter(new MaxRPZofReporter(noEntry));
            if(!minRPZofQMB.isEmpty()) publication.setMinRPZofQMB(new MinRPZofQMB(minRPZofQMB.get(i)));
            else publication.setMinRPZofQMB(new MinRPZofQMB(noEntry));
            if(!avgRPZofQMB.isEmpty()) publication.setAvgRPZofQMB(new AvgRPZofQMB(avgRPZofQMB.get(i)));
            else publication.setAvgRPZofQMB(new AvgRPZofQMB(noEntry));
            if(!maxRPZofQMB.isEmpty()) publication.setMaxRPZofQMB(new MaxRPZofQMB(maxRPZofQMB.get(i)));
            else publication.setMaxRPZofQMB(new MaxRPZofQMB(noEntry));
            if(!category.isEmpty()) publication.setCategory(new Category(category.get(i)));
            else publication.setCategory(new Category(noEntry));
            if(!action.isEmpty()) publication.setAction(new Action(action.get(i)));
            else publication.setAction(new Action(noEntry));
            if(!assignedReports.isEmpty()) publication.setAssignedReports(new AssignedReports(assignedReports.get(i)));
            else publication.setAssignedReports(new AssignedReports(noEntry));
        }

        return publication;
    }

    public List<Publication> parsePublication(String xml) {
        ParseXML2LIST p = new ParseXML2LIST();

        List<String> pubID;
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

        List<Publication> publications = new LinkedList<>();
        for (int i = 0; i < pubID.size(); i++) {
            Publication publication = new Publication();
            if(pubID.get(i) != null) publication.setPubID(new PubID(pubID.get(i)));
            if(entryDate.get(i) != null) publication.setEntryDate(new EntryDate(entryDate.get(i)));
            if(revisionDate.get(i) != null) publication.setRevisionDate(new RevisionDate(revisionDate.get(i)));
            if(title.get(i) != null) publication.setTitle(new Title(title.get(i)));
            if(status.get(i) != null) publication.setStatus(new Status(status.get(i)));
            if(numberOfReports.get(i) != null) publication.setNumberOfReports(new NumberOfReports(numberOfReports.get(i)));
            if(numberOfComments.get(i) != null) publication.setNumberOfComments(new NumberOfComments(numberOfComments.get(i)));
            publications.add(publication);

        }

        return publications;
    }


}

