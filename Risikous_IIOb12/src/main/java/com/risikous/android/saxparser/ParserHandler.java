package com.risikous.android.saxparser;

import com.risikous.android.saxparser.getParser.ParserID;
import com.risikous.android.saxparser.getParser.ParserQuestionnaire;
import com.risikous.android.saxparser.getParser.ParserReportingAreas;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;


/**
 * Created by Excel on 17.12.2014.
 */

public class ParserHandler {

    public static void main(String args[]) {

        final File file = new File("C:/Users/Excel/IdeaProjects/risikous/src/com/risikous/android/saxparser/test.xml");

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {

                    if (tagName.equalsIgnoreCase("STATUS")) {
                        System.out.println("Start Element :" + tagName);
                        ParserID id = new ParserID(file);
                        System.out.println("End Element :" + tagName);
                    }

                    if (tagName.equalsIgnoreCase("QUESTIONNAIRE")) {
                        System.out.println("Start Element :" + tagName);
                        ParserQuestionnaire questionnaire = new ParserQuestionnaire(file);
                        System.out.println("End Element :" + tagName);
                    }

                    if (tagName.equalsIgnoreCase("REPORTINGAREAS")) {
                        System.out.println("Start Element :" + tagName);
                        ParserReportingAreas reportingAreas = new ParserReportingAreas(file);
                        System.out.println("End Element :" + tagName);
                    }

                }
            };

            saxParser.parse(file, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
