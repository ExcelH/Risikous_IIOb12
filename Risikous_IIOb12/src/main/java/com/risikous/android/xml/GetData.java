package com.risikous.android.xml;

import android.app.Activity;
import android.os.AsyncTask;

import com.risikous.android.model.comment.Comment;
import com.risikous.android.request.GetRequest;
import com.risikous.android.sqlite.SQLiteHelper_Comment;
import com.risikous.android.sqlite.SQLiteHelper_Publication;
import com.risikous.android.sqlite.SQLiteHelper_SubComment;
import com.risikous.android.url_uri.Constants;
import com.risikous.android.xml.parser.ParseComment;
import com.risikous.android.xml.parser.ParsePublication;
import com.risikous.android.xml.parser.ParseSubComment;

import java.util.List;

/**
 * Created by Excel on 29.03.2015.
 */
public class GetData {

    Activity activity;

    private interface ResponseCallback {
        public void onResponse(String s);

    }

    public GetData(Activity activity) {

        this.activity = activity;
        new GET(new ResponseCallback() {
            @Override
            public void onResponse(String s) {
                GetPublication(s);
            }
        }, Constants.PUBLICATION_GET_URL).execute();
    }

    public class GET extends AsyncTask<Void, Void, String> {


        private String data = null;
        private ResponseCallback responseCallback;
        private String url = null;


        public GET(ResponseCallback callback, String url) {
            this.url = url;
            responseCallback = callback;
        }

        @Override
        protected String doInBackground(Void... arg0) {
            GetRequest gr = new GetRequest();
            return gr.GetXML(url);
        }

        @Override
        protected void onPostExecute(String result) {
            if (responseCallback != null)
                responseCallback.onResponse(result);

        }
    }

    private void GetPublication(String xml) {

        SQLiteHelper_Publication db = new SQLiteHelper_Publication(activity);
        ParsePublication p = new ParsePublication(activity);
        p.parsePublication(xml, db);
        db.close();
    }

}
