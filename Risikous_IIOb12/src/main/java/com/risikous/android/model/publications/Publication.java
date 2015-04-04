package com.risikous.android.model.publications;


import com.risikous.android.model.publications.part.*;


/**
 * Created by Excel on 09.01.2015.
 */

public class Publication {
    int ID = -1;
    private EntryDate entryDate;
    private String entryDateDB;
    private PubID pubID;
    private String pubIDDB;
    private NumberOfReports numberOfReports;
    private String numberOfReportsDB;
    private NumberOfComments numberOfComments;
    private String numberOfCommentsDB;
    private RevisionDate revisionDate;
    private String revisionDateDB;
    private Status status;
    private String statusDB;
    private Title title;
    private String titleDB;
    private IncidentReport incidentReport;
    private String incidentReportDB;
    private MinRPZofReporter minRPZofReporter;
    private String minRPZofReporterDB;
    private AvgRPZofReporter avgRPZofReporter;
    private String avgRPZofReporterDB;
    private MaxRPZofReporter maxRPZofReporter;
    private String maxRPZofReporterDB;
    private MinRPZofQMB minRPZofQMB;
    private String minRPZofQMBDB;
    private AvgRPZofQMB avgRPZofQMB;
    private String avgRPZofQMBDB;
    private MaxRPZofQMB maxRPZofQMB;
    private String maxRPZofQMBDB;
    private Category category;
    private String categoryDB;
    private Action action;
    private String actionDB;
    private AssignedReports assignedReports;
    private String assignedReportsDB;

    public Publication() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getEntryDateDB() {
        return entryDateDB;
    }

    public void setEntryDateDB(String entryDateDB) {
        this.entryDateDB = entryDateDB;
    }

    public String getPubIDDB() {
        return pubIDDB;
    }

    public void setPubIDDB(String pubIDDB) {
        this.pubIDDB = pubIDDB;
    }

    public String getNumberOfReportsDB() {
        return numberOfReportsDB;
    }

    public void setNumberOfReportsDB(String numberOfReportsDB) {
        this.numberOfReportsDB = numberOfReportsDB;
    }

    public String getNumberOfCommentsDB() {
        return numberOfCommentsDB;
    }

    public void setNumberOfCommentsDB(String numberOfCommentsDB) {
        this.numberOfCommentsDB = numberOfCommentsDB;
    }

    public String getRevisionDateDB() {
        return revisionDateDB;
    }

    public void setRevisionDateDB(String revisionDateDB) {
        this.revisionDateDB = revisionDateDB;
    }

    public String getStatusDB() {
        return statusDB;
    }

    public void setStatusDB(String statusDB) {
        this.statusDB = statusDB;
    }

    public String getTitleDB() {
        return titleDB;
    }

    public void setTitleDB(String titleDB) {
        this.titleDB = titleDB;
    }

    public String getIncidentReportDB() {
        return incidentReportDB;
    }

    public void setIncidentReportDB(String incidentReportDB) {
        this.incidentReportDB = incidentReportDB;
    }

    public String getMinRPZofReporterDB() {
        return minRPZofReporterDB;
    }

    public void setMinRPZofReporterDB(String minRPZofReporterDB) {
        this.minRPZofReporterDB = minRPZofReporterDB;
    }

    public String getAvgRPZofReporterDB() {
        return avgRPZofReporterDB;
    }

    public void setAvgRPZofReporterDB(String avgRPZofReporterDB) {
        this.avgRPZofReporterDB = avgRPZofReporterDB;
    }

    public String getMaxRPZofReporterDB() {
        return maxRPZofReporterDB;
    }

    public void setMaxRPZofReporterDB(String maxRPZofReporterDB) {
        this.maxRPZofReporterDB = maxRPZofReporterDB;
    }

    public String getMinRPZofQMBDB() {
        return minRPZofQMBDB;
    }

    public void setMinRPZofQMBDB(String minRPZofQMBDB) {
        this.minRPZofQMBDB = minRPZofQMBDB;
    }

    public String getAvgRPZofQMBDB() {
        return avgRPZofQMBDB;
    }

    public void setAvgRPZofQMBDB(String avgRPZofQMBDB) {
        this.avgRPZofQMBDB = avgRPZofQMBDB;
    }

    public String getMaxRPZofQMBDB() {
        return maxRPZofQMBDB;
    }

    public void setMaxRPZofQMBDB(String maxRPZofQMBDB) {
        this.maxRPZofQMBDB = maxRPZofQMBDB;
    }

    public String getCategoryDB() {
        return categoryDB;
    }

    public void setCategoryDB(String categoryDB) {
        this.categoryDB = categoryDB;
    }

    public String getActionDB() {
        return actionDB;
    }

    public void setActionDB(String actionDB) {
        this.actionDB = actionDB;
    }

    public String getAssignedReportsDB() {
        return assignedReportsDB;
    }

    public void setAssignedReportsDB(String assignedReportsDB) {
        this.assignedReportsDB = assignedReportsDB;
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
