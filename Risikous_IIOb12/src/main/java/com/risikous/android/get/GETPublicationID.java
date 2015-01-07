package com.risikous.android.get;

/**
 * Created by Excel on 06.12.2014.
 */
public class GETPublicationID {

    private static final String URL_PART_1 = " http://94.101.38.155/RisikousRESTful/rest/publication?id=<";
    private static final String URL_PART_2 = ">";

    private int mID = -1;

    public GETPublicationID(int id) {
        mID = id;
    }

    public String getURL(){
        return URL_PART_1 + mID + URL_PART_2;
    }


}
