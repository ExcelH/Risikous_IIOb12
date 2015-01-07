package com.risikous.android.saxparser.getParser;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Excel on 17.12.2014.
 */

public class ParserReportingAreas {

    public ParserReportingAreas(File file) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean shortCut = false;
                boolean name = false;

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {


                    if (tagName.equalsIgnoreCase("SHORTCUT")) {
                        shortCut = true;
                    }

                    if (tagName.equalsIgnoreCase("NAME")) {
                        name = true;
                    }
                }

                public void endElement(String uri, String localName,
                                       String tagName) throws SAXException {

                    if (tagName.equalsIgnoreCase("REPORTINGAREA")) {

                        System.out.println("End Element :" + tagName);
                    }


                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (shortCut) {
                        System.out.println("Shortcut : " + new String(ch, start, length));
                        shortCut = false;
                    }

                    if (name) {
                        System.out.println("Name : " + new String(ch, start, length));
                        name = false;
                    }
                }

            };

            saxParser.parse(file, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
