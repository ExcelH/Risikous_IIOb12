package com.risikous.android.url;

/**
 * Created by Dominik on 11.01.2015.
 */
public class Constants {

    public static final String PUBLICATION_GET_URL = "http://94.101.38.155/RisikousRESTful/rest/publications";
    public static final String PUBLICATION_POST_URL = "http://94.101.38.155/RisikousRESTful/rest/questionnaire/addQuestionnaire";
    //public static final String PUBLICATION_POST_URL = "http://posttestserver.com/post.php";
    //public static final String COMMENT_POST_URL = "http://94.101.38.155/RisikousRESTful/rest/publication/addComment";
    public static final String COMMENT_POST_URL = "http://posttestserver.com/post.php";
    //public static final String SUBCOMMENT_POST_URL = "http://94.101.38.155/RisikousRESTful/rest/comment/addAnswer";
    public static final String SUBCOMMENT_POST_URL = "http://posttestserver.com/post.php";

    public static String COMMENT_GET_URL(String id) {
        return "http://94.101.38.155/RisikousRESTful/rest/comments?id=" + id;
    }

    public static String PUBLICATION_GET_FULL_PUB_URL(String id) {
        return "http://94.101.38.155/RisikousRESTful/rest/publication?id=" + id;
    }


}
