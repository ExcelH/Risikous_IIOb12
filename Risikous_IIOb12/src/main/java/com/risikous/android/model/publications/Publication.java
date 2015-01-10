package com.risikous.android.model.publications;


import com.risikous.android.model.publications.part.*;


/**
 * Created by Excel on 09.01.2015.
 */

public class Publication {
    private EntryDate entryDate;
    private PubID pubID;
    private NumberOfReports numberOfReports;
    private NumberOfComments numberOfComments;
    private RevisionDate revisionDate;
    private Status status;
    private Title title;

    public Publication(EntryDate entryDate, PubID pubID, NumberOfReports numberOfReports, NumberOfComments numberOfComments, RevisionDate revisionDate, Status status, Title title) {
        this.entryDate = entryDate;
        this.pubID = pubID;
        this.numberOfReports = numberOfReports;
        this.numberOfComments = numberOfComments;
        this.revisionDate = revisionDate;
        this.status = status;
        this.title = title;
    }

    public Publication() {
    }

    public EntryDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(EntryDate entryDate) {
        this.entryDate = entryDate;
    }

    public PubID getPubID() {
        return pubID;
    }

    public void setPubID(PubID pubID) {
        this.pubID = pubID;
    }

    public NumberOfReports getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(NumberOfReports numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public NumberOfComments getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(NumberOfComments numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public RevisionDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(RevisionDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "entryDate=" + entryDate +
                ", pubID=" + pubID +
                ", numberOfReports=" + numberOfReports +
                ", numberOfComments=" + numberOfComments +
                ", revisionDate=" + revisionDate +
                ", status=" + status +
                ", title=" + title +
                '}';
    }
}
