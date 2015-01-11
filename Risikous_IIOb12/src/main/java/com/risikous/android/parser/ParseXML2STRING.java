package com.risikous.android.parser;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;

/**
 * Created by Excel on 10.01.2015.
 */
public class ParseXML2STRING {
    String string = "";
    public String parseSubXML(String xml, final String target) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean tag = false;

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {


                    if (tagName.equalsIgnoreCase(target)) {
                        tag = true;
                    }

                }

                public void endElement(String uri, String localName,
                                       String tagName) throws SAXException {

                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (tag) {
                        string = string + new String(ch, start, length);
                        tag = false;
                    }

                }

            };

            saxParser.parse(new InputSource(new StringReader(xml)), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return string;
    }
}