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

        List<String> title = new LinkedList<>();
        List<String> incidentReporter = new LinkedList<>();
        List<String> minRPZofReporter = new LinkedList<>();
        List<String> avgRPZofReporter = new LinkedList<>();
        List<String> maxRPZofReporter = new LinkedList<>();
        List<String> minRPZofQMB = new LinkedList<>();
        List<String> avgRPZofQMB = new LinkedList<>();
        List<String> maxRPZofQMB = new LinkedList<>();
        List<String> category = new LinkedList<>();
        List<String> action = new LinkedList<>();
        List<String> assignedReports = new LinkedList<>();

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
            publication.setTitle(new Title(title.get(i)));
            publication.setIncidentReport(new IncidentReport(incidentReporter.get(i)));
            publication.setMinRPZofReporter(new MinRPZofReporter(minRPZofReporter.get(i)));
            publication.setAvgRPZofReporter(new AvgRPZofReporter(avgRPZofReporter.get(i)));
            publication.setMaxRPZofReporter(new MaxRPZofReporter(maxRPZofReporter.get(i)));
            publication.setMinRPZofQMB(new MinRPZofQMB(minRPZofQMB.get(i)));
            publication.setAvgRPZofQMB(new AvgRPZofQMB(avgRPZofQMB.get(i)));
            publication.setMaxRPZofQMB(new MaxRPZofQMB(maxRPZofQMB.get(i)));
            publication.setCategory(new Category(category.get(i)));
            publication.setAction(new Action(action.get(i)));
            publication.setAssignedReports(new AssignedReports(assignedReports.get(i)));


        }

        return publication;
    }

    public List<Publication> parsePublication(String xml) {
        ParseXML2LIST p = new ParseXML2LIST();

        List<String> pubID = new LinkedList<>();
        List<String> entryDate = new LinkedList<>();
        List<String> revisionDate = new LinkedList<>();
        List<String> title = new LinkedList<>();
        List<String> status = new LinkedList<>();
        List<String> numberOfReports = new LinkedList<>();
        List<String> numberOfComments = new LinkedList<>();

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
            publication.setPubID(new PubID(pubID.get(i)));
            publication.setEntryDate(new EntryDate(entryDate.get(i)));
            publication.setRevisionDate(new RevisionDate(revisionDate.get(i)));
            publication.setTitle(new Title(title.get(i)));
            publication.setStatus(new Status(status.get(i)));
            publication.setNumberOfReports(new NumberOfReports(numberOfReports.get(i)));
            publication.setNumberOfComments(new NumberOfComments(numberOfComments.get(i)));
            publications.add(publication);

        }

        return publications;
    }


}

