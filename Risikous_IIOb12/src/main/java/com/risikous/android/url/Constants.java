package com.risikous.android.url;

/**
 * Created by Dominik on 11.01.2015.
 */
public class Constants {
    public static final String PUBLICATION_URL = "http://94.101.38.155/RisikousRESTful/rest/publications";
    public static String COMMENT_URL(String id){
        return "http://94.101.38.155/RisikousRESTful/rest/comments?id="+id;
    }

}