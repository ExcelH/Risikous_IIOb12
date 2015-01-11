package com.risikous.android.parser;

import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.comment.part.TimeStamp;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Excel on 10.01.2015.
 */
public class ParseXML2STRING {
    private List<Comment> mSubComments = new ArrayList<Comment>();

    private boolean mIsAuthor;
    private boolean mIsText;
    private boolean mIsTimeStamp;
    private boolean mIsMainCommentID;
    private boolean mIsTarget;

    private Comment mNewComment;
    private String mCurrentCommentID;

    public List<Comment> parseSubXML(String xml, final String target) {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName, String tagName,
                                         Attributes attributes) throws SAXException {


                    if (tagName.equalsIgnoreCase("id"))
                        mIsMainCommentID = true;

                    if (mIsTarget) {
                        if (tagName.equalsIgnoreCase("author"))
                            mIsAuthor = true;
                        else if (tagName.equalsIgnoreCase("text"))
                            mIsText = true;
                        else if (tagName.equalsIgnoreCase("timeStamp"))
                            mIsTimeStamp = true;
                    }

                    if (tagName.equalsIgnoreCase(target)) {
                        mIsTarget = true;
                        mNewComment = new Comment();
                        PubID pubID = new PubID();
                        pubID.setName(mCurrentCommentID);
                        mNewComment.setPubID(pubID);
                    }

                }

                public void endElement(String uri, String localName,
                                       String tagName) throws SAXException {
                    if (tagName.equalsIgnoreCase(target)) {
                        mIsTarget = false;
                        mSubComments.add(mNewComment);
                        mNewComment = null;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (mIsMainCommentID) {
                        mCurrentCommentID = new String(ch, start, length);
                        mIsMainCommentID = false;
                    }
                    if (mIsTarget) {
                        if (mIsAuthor) {
                            Author author = new Author();
                            author.setName(new String(ch, start, length));
                            mNewComment.setAuthor(author);
                            mIsAuthor = false;
                        } else if (mIsText) {
                            Text text = new Text();
                            text.setName(new String(ch, start, length));
                            mNewComment.setText(text);
                            mIsText = false;
                        } else if (mIsTimeStamp) {
                            TimeStamp timeStamp = new TimeStamp();
                            timeStamp.setName(new String(ch, start, length));
                            mNewComment.setTimeStamp(timeStamp);
                            mIsTimeStamp = false;
                        }

                    }

                }

            };

            saxParser.parse(new InputSource(new StringReader(xml)), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mSubComments;
    }

}