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
    private IncidentReport incidentReport;
    private MinRPZofReporter minRPZofReporter;
    private AvgRPZofReporter avgRPZofReporter;
    private MaxRPZofReporter maxRPZofReporter;
    private MinRPZofQMB minRPZofQMB;
    private AvgRPZofQMB avgRPZofQMB;
    private MaxRPZofQMB maxRPZofQMB;
    private Category category;
    private Action action;
    private AssignedReports assignedReports;

    public Publication(EntryDate entryDate, PubID pubID, NumberOfReports numberOfReports, NumberOfComments numberOfComments, RevisionDate revisionDate, Status status, Title title, IncidentReport incidentReport, MinRPZofReporter minRPZofReporter, AvgRPZofReporter avgRPZofReporter, MaxRPZofReporter maxRPZofReporter, MinRPZofQMB minRPZofQMB, AvgRPZofQMB avgRPZofQMB, MaxRPZofQMB maxRPZofQMB, Category category, Action action, AssignedReports assignedReports) {
        this.entryDate = entryDate;
        this.pubID = pubID;
        this.numberOfReports = numberOfReports;
        this.numberOfComments = numberOfComments;
        this.revisionDate = revisionDate;
        this.status = status;
        this.title = title;
        this.incidentReport = incidentReport;
        this.minRPZofReporter = minRPZofReporter;
        this.avgRPZofReporter = avgRPZofReporter;
        this.maxRPZofReporter = maxRPZofReporter;
        this.minRPZofQMB = minRPZofQMB;
        this.avgRPZofQMB = avgRPZofQMB;
        this.maxRPZofQMB = maxRPZofQMB;
        this.category = category;
        this.action = action;
        this.assignedReports = assignedReports;
    }

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

    public IncidentReport getIncidentReport() {

        return incidentReport;
    }

    public void setIncidentReport(IncidentReport incidentReport) {
        this.incidentReport = incidentReport;
    }

    public MinRPZofReporter getMinRPZofReporter() {
        return minRPZofReporter;
    }

    public void setMinRPZofReporter(MinRPZofReporter minRPZofReporter) {
        this.minRPZofReporter = minRPZofReporter;
    }

    public AvgRPZofReporter getAvgRPZofReporter() {
        return avgRPZofReporter;
    }

    public void setAvgRPZofReporter(AvgRPZofReporter avgRPZofReporter) {
        this.avgRPZofReporter = avgRPZofReporter;
    }

    public MaxRPZofReporter getMaxRPZofReporter() {
        return maxRPZofReporter;
    }

    public void setMaxRPZofReporter(MaxRPZofReporter maxRPZofReporter) {
        this.maxRPZofReporter = maxRPZofReporter;
    }

    public MinRPZofQMB getMinRPZofQMB() {
        return minRPZofQMB;
    }

    public void setMinRPZofQMB(MinRPZofQMB minRPZofQMB) {
        this.minRPZofQMB = minRPZofQMB;
    }

    public AvgRPZofQMB getAvgRPZofQMB() {
        return avgRPZofQMB;
    }

    public void setAvgRPZofQMB(AvgRPZofQMB avgRPZofQMB) {
        this.avgRPZofQMB = avgRPZofQMB;
    }

    public MaxRPZofQMB getMaxRPZofQMB() {
        return maxRPZofQMB;
    }

    public void setMaxRPZofQMB(MaxRPZofQMB maxRPZofQMB) {
        this.maxRPZofQMB = maxRPZofQMB;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public AssignedReports getAssignedReports() {
        return assignedReports;
    }

    public void setAssignedReports(AssignedReports assignedReports) {
        this.assignedReports = assignedReports;
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
