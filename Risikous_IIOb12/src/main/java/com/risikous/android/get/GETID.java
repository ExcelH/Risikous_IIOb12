package com.risikous.android.get;

import com.risikous.android.interfaces.InterfaceGET;

/**
 * Created by Excel on 06.12.2014.
 */

public class GETID implements InterfaceGET {

    private static final String tagID= "STATUS";
    private static final String URL_PART_1 = "http://94.101.38.155/RisikousRESTful/rest/status?id=<";
    private static final String URL_PART_2 = ">";

    private int mID = -1;

    public GETID(int id) {
        mID = id;
    }

    public String getURL(){
        return URL_PART_1 + mID + URL_PART_2;
    }

    @Override
    public String getTagID() {
        return tagID;
    }
}
