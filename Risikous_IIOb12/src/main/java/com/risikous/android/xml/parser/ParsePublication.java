package com.risikous.android.xml.parser;


import com.risikous.android.model.publications.Publication;
import com.risikous.android.model.publications.part.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParsePublication {

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

