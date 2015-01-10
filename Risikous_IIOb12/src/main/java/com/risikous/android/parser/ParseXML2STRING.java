package com.risikous.android.parser;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Excel on 10.01.2015.
 */
public class ParseXML2STRING {
    public List<String> parseXML(String xml, final String target, final String validationType) {
        final List<String> list = new LinkedList<>();
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean tag = false;

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {


                    if (tagName.equalsIgnoreCase(target)) {
                        tag = true;
                        list.add(attributes.getValue(validationType));
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
