package com.risikous.android.parser;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Excel on 10.01.2015.
 */
public class ParseXML2LIST {
    public List<String> parseXML(String xml, final String target) {
        final List<String> list = new LinkedList<>();
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean tag = false;
                String string = null;

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
                        string = new String(ch, start, length);
                        try {
                            list.add(new String(string.getBytes("ISO-8859-1"), "UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        tag = false;
                    }

                }

            };

            saxParser.parse(new InputSource(new StringReader(xml)), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
