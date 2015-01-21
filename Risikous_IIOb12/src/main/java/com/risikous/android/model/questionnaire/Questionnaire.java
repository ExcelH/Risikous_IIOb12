package com.risikous.android.model.questionnaire;

import com.risikous.android.model.questionnaire.part.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Excel on 10.01.2015.
 */
public class Questionnaire {
    private AdditionalNotes additionalNotes;
    private Consequences consequences;
    private ContactInformation contactInformation;
    private Date date;
    private DetectionRating detectionRating;
    private List<File> fileCollection = null;
    private ImmediateMeasure immediateMeasure;
    private IncidentDescription incidentDescription;
    private Location location;
    private OccurrenceRating occurrenceRating;
    private OrganisationalFactors organisationalFactors;
    private PersonalFactors personalFactors;
    private ReportingArea reportingArea;
    private Significance significance;
    private Time time;
    private File file = new File();
    private PointOfTime pointOfTime = new PointOfTime();
    private Files files = new Files();
    private RiskEstimation riskEstimation = new RiskEstimation();
    private OpinionOfReporter opinionOfReporter = new OpinionOfReporter();
    private String tagName = "questionnaire";

    public Questionnaire(AdditionalNotes additionalNotes, Consequences consequences, ContactInformation contactInformation, Date date, DetectionRating detectionRating, File file, Files files, ImmediateMeasure immediateMeasure, IncidentDescription incidentDescription, Location location, OccurrenceRating occurrenceRating, OpinionOfReporter opinionOfReporter, OrganisationalFactors organisationalFactors, PersonalFactors personalFactors, PointOfTime pointOfTime, ReportingArea reportingArea, RiskEstimation riskEstimation, Significance significance, Time time, String tagName) {
        this.additionalNotes = additionalNotes;
        this.consequences = consequences;
        this.contactInformation = contactInformation;
        this.date = date;
        this.detectionRating = detectionRating;
        this.file = file;
        this.files = files;
        this.immediateMeasure = immediateMeasure;
        this.incidentDescription = incidentDescription;
        this.location = location;
        this.occurrenceRating = occurrenceRating;
        this.opinionOfReporter = opinionOfReporter;
        this.organisationalFactors = organisationalFactors;
        this.personalFactors = personalFactors;
        this.pointOfTime = pointOfTime;
        this.reportingArea = reportingArea;
        this.riskEstimation = riskEstimation;
        this.significance = significance;
        this.time = time;
        this.tagName = tagName;
    }

    public String getTagName() {

        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Questionnaire() {
    }

    public Questionnaire(AdditionalNotes additionalNotes, Consequences consequences, ContactInformation contactInformation, Date date, DetectionRating detectionRating, File file, Files files, ImmediateMeasure immediateMeasure, IncidentDescription incidentDescription, Location location, OccurrenceRating occurrenceRating, OpinionOfReporter opinionOfReporter, OrganisationalFactors organisationalFactors, PersonalFactors personalFactors, PointOfTime pointOfTime, ReportingArea reportingArea, RiskEstimation riskEstimation, Significance significance, Time time) {
        this.additionalNotes = additionalNotes;
        this.consequences = consequences;
        this.contactInformation = contactInformation;
        this.date = date;
        this.detectionRating = detectionRating;
        this.file = file;
        this.files = files;
        this.immediateMeasure = immediateMeasure;
        this.incidentDescription = incidentDescription;
        this.location = location;
        this.occurrenceRating = occurrenceRating;
        this.opinionOfReporter = opinionOfReporter;
        this.organisationalFactors = organisationalFactors;
        this.personalFactors = personalFactors;
        this.pointOfTime = pointOfTime;
        this.reportingArea = reportingArea;
        this.riskEstimation = riskEstimation;
        this.significance = significance;
        this.time = time;
    }

    public List<File> getFileCollection() {
        return fileCollection;
    }

    public void setFileCollection(List<File> fileCollection) {
        this.fileCollection = fileCollection;
    }

    public void addAttachment(File file){
        if(fileCollection == null) this.fileCollection = new ArrayList<File>();
        fileCollection.add(file);
    }

    public void removeObjectFromList(String name){
        for (int i=0; i<fileCollection.size(); i++) {
            String string = fileCollection.get(i).getName();
            if (string.equals(name)){
                fileCollection.remove(i);
            }
        }
    }

    public AdditionalNotes getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(AdditionalNotes additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public Consequences getConsequences() {
        return consequences;
    }

    public void setConsequences(Consequences consequences) {
        this.consequences = consequences;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DetectionRating getDetectionRating() {
        return detectionRating;
    }

    public void setDetectionRating(DetectionRating detectionRating) {
        this.detectionRating = detectionRating;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Files getFiles() {
        return files;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public ImmediateMeasure getImmediateMeasure() {
        return immediateMeasure;
    }

    public void setImmediateMeasure(ImmediateMeasure immediateMeasure) {
        this.immediateMeasure = immediateMeasure;
    }

    public IncidentDescription getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(IncidentDescription incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public OccurrenceRating getOccurrenceRating() {
        return occurrenceRating;
    }

    public void setOccurrenceRating(OccurrenceRating occurrenceRating) {
        this.occurrenceRating = occurrenceRating;
    }

    public OpinionOfReporter getOpinionOfReporter() {
        return opinionOfReporter;
    }

    public void setOpinionOfReporter(OpinionOfReporter opinionOfReporter) {
        this.opinionOfReporter = opinionOfReporter;
    }

    public OrganisationalFactors getOrganisationalFactors() {
        return organisationalFactors;
    }

    public void setOrganisationalFactors(OrganisationalFactors organisationalFactors) {
        this.organisationalFactors = organisationalFactors;
    }

    public PersonalFactors getPersonalFactors() {
        return personalFactors;
    }

    public void setPersonalFactors(PersonalFactors personalFactors) {
        this.personalFactors = personalFactors;
    }

    public PointOfTime getPointOfTime() {
        return pointOfTime;
    }

    public void setPointOfTime(PointOfTime pointOfTime) {
        this.pointOfTime = pointOfTime;
    }

    public ReportingArea getReportingArea() {
        return reportingArea;
    }

    public void setReportingArea(ReportingArea reportingArea) {
        this.reportingArea = reportingArea;
    }

    public RiskEstimation getRiskEstimation() {
        return riskEstimation;
    }

    public void setRiskEstimation(RiskEstimation riskEstimation) {
        this.riskEstimation = riskEstimation;
    }

    public Significance getSignificance() {
        return significance;
    }

    public void setSignificance(Significance significance) {
        this.significance = significance;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
