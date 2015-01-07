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

public class ParserID {

    public ParserID(File file) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean code = false;
                boolean publicationID = false;
                boolean text = false;

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {


                    if (tagName.equalsIgnoreCase("CODE")) {
                        code = true;
                    }

                    if (tagName.equalsIgnoreCase("TEXT")) {
                        text = true;
                    }

                    if (tagName.equalsIgnoreCase("PUBLICATIONID")) {
                        publicationID = true;
                    }
                }


                public void characters(char ch[], int start, int length) throws SAXException {

                    if (code) {
                        System.out.println("Code : " + new String(ch, start, length));
                        code = false;
                    }

                    if (text) {
                        System.out.println("Text : " + new String(ch, start, length));
                        text = false;
                    }

                    if (publicationID) {
                        System.out.println("PublicationID : " + new String(ch, start, length));
                        publicationID = false;
                    }
                }

            };

            saxParser.parse(file, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


