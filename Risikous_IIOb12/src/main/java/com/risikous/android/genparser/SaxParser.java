package com.risikous.android.genparser;

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
 * Created by Excel on 18.12.2014.
 */
public class SaxParser {

    final File file = new File("C:/Users/Excel/IdeaProjects/risikous/src/com/risikous/android/genparser/test.xml");

    public SaxParser(final String tagID) {

        try

        {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {

                    if (tagName.equalsIgnoreCase(tagID)) {

                    }

                }
            };

            saxParser.parse(file, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}