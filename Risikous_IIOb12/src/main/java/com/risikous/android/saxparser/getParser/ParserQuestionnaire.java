package com.risikous.android.saxparser.getParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * Created by Excel on 17.12.2014.
 */
public class ParserQuestionnaire {

    public ParserQuestionnaire(File file) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean riskEstimation = false;
                boolean pointOfTime = false;
                boolean opinionOfReporter = false;
                boolean files = false;

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {


                    if (tagName.equalsIgnoreCase("REPORTINGAREA") ||
                            tagName.equalsIgnoreCase("OCCURRENCERATING") ||
                            tagName.equalsIgnoreCase("DETECTIONRATING") ||
                            tagName.equalsIgnoreCase("SIGNIFICANCE")) {

                        System.out.println(tagName + "  " + attributes.getValue("required") + "  " + attributes.getValue("text"));
                    }

                    if (tagName.equalsIgnoreCase("INCIDENTDESCRIPTION") ||
                            tagName.equalsIgnoreCase("REPORTINGAREA") ||
                            tagName.equalsIgnoreCase("OPINIONOFREPORTER")) {

                        System.out.println(tagName + "  " + attributes.getValue("text"));
                    }

                    if (tagName.equalsIgnoreCase("DATE") ||
                            tagName.equalsIgnoreCase("TIME")) {

                        System.out.println(tagName + "  " + attributes.getValue("format") + "  " + attributes.getValue("text"));
                    }

                    if (tagName.equalsIgnoreCase("LOCATION") ||
                            tagName.equalsIgnoreCase("IMMEDIATEMEASURE") ||
                            tagName.equalsIgnoreCase("CONSEQUENCES") ||
                            tagName.equalsIgnoreCase("PERSONALFACTORS") ||
                            tagName.equalsIgnoreCase("ORGANISATIONALFACTORS") ||
                            tagName.equalsIgnoreCase("ADDITIONALNOTES") ||
                            tagName.equalsIgnoreCase("CONTACTINFORMATION")) {

                        System.out.println(tagName + "  " + attributes.getValue("maximumOfCharacters") + "  " + attributes.getValue("text"));
                    }

                    if (tagName.equalsIgnoreCase("FILES")) {

                        System.out.println(tagName + "  " + attributes.getValue("annotation") + "  " + attributes.getValue("maximumSizeInMB") + "  " + attributes.getValue("text"));
                    }

                    if (tagName.equalsIgnoreCase("FILE")) {

                        System.out.println(tagName + "  " + attributes.getValue("encoding") + "  " + attributes.getValue("name"));
                    }
                }


            };

            saxParser.parse(file, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
