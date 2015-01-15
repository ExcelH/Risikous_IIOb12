package com.risikous.android.xml.builder;

import com.risikous.android.model.questionnaire.Questionnaire;

/**
 * Created by Excel on 13.01.2015.
 */
public class BuildPublication {

    public String buildQuestionnaire(Questionnaire q) {

        String questionnaireString = "";
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        BuildXMLTag build = new BuildXMLTag();
        StringBuilder sB = new StringBuilder();

        //required
        questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getReportingArea().getTagName(), q.getReportingArea().getName()));
        questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getIncidentDescription().getTagName(),q.getIncidentDescription().getName()));
        questionnaireString = sB.connectString(questionnaireString, "<" + q.getRiskEstimation().getTagName() + ">");
        questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getOccurrenceRating().getTagName(), q.getOccurrenceRating().getName()));
        questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getDetectionRating().getTagName(), q.getDetectionRating().getName()));
        questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getSignificance().getTagName(), q.getSignificance().getName()));
        questionnaireString = sB.connectString(questionnaireString, "</" + q.getRiskEstimation().getTagName() + ">");

        //optional
        if(!q.getDate().getName().isEmpty()||!q.getTime().getName().isEmpty()){
            questionnaireString = sB.connectString(questionnaireString, "<" + q.getPointOfTime().getTagName() + ">");
            if(!q.getDate().getName().isEmpty())
                questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getDate().getTagName(), q.getDate().getName()));
            if(!q.getTime().getName().isEmpty())
                questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getTime().getTagName(), q.getTime().getName()));
            questionnaireString = sB.connectString(questionnaireString, "</" + q.getPointOfTime().getTagName() + ">");
        }
        if(!q.getLocation().getName().isEmpty())
            questionnaireString = sB.connectString(questionnaireString, build.buildXMLTag(q.getLocation().getTagName(), q.getLocation().getName()));
        if(!q.getImmediateMeasure().getName().isEmpty())
            questionnaireString = sB.connectString(questionnaireString, build.buildXMLTag(q.getImmediateMeasure().getTagName(), q.getImmediateMeasure().getName()));
        if(!q.getConsequences().getName().isEmpty())
            questionnaireString = sB.connectString(questionnaireString, build.buildXMLTag(q.getConsequences().getTagName(), q.getConsequences().getName()));
        if(!q.getPersonalFactors().getName().isEmpty()||!q.getOrganisationalFactors().getName().isEmpty()||!q.getAdditionalNotes().getName().isEmpty()){
            questionnaireString = sB.connectString(questionnaireString, "<" + q.getOpinionOfReporter().getTagName() + ">");
            if(!q.getPersonalFactors().getName().isEmpty())
                questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getPersonalFactors().getTagName(), q.getPersonalFactors().getName()));
            if(!q.getOrganisationalFactors().getName().isEmpty())
                questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getOrganisationalFactors().getTagName(), q.getOrganisationalFactors().getName()));
            if(!q.getAdditionalNotes().getName().isEmpty())
                questionnaireString = sB.connectString(questionnaireString,build.buildXMLTag(q.getAdditionalNotes().getTagName(), q.getAdditionalNotes().getName()));
            questionnaireString = sB.connectString(questionnaireString, "</" + q.getOpinionOfReporter().getTagName() + ">");
        }
        if(!q.getFile().getName().isEmpty()){
            questionnaireString = sB.connectString(questionnaireString, "<" + q.getFiles().getTagName() + ">");
            questionnaireString = sB.connectString(questionnaireString, build.buildXMLTag(q.getFile().getTagName(), q.getFile().getName()));
            questionnaireString = sB.connectString(questionnaireString, "</" + q.getFiles().getTagName() + ">");
        }
        if(!q.getContactInformation().getName().isEmpty())
            questionnaireString = sB.connectString(questionnaireString, build.buildXMLTag(q.getContactInformation().getTagName(), q.getContactInformation().getName()));

        questionnaireString = build.buildXMLTag(q.getTagName(),questionnaireString);
        questionnaireString = sB.connectString(xmlHeader, questionnaireString);

        return questionnaireString;
    }
}
