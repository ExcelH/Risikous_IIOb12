package com.risikous.android.request;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

/**
 * Created by Excel on 11.01.2015.
 */
public class PostRequest {

    public String postXML(String url, String xml) {

        HttpClient client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/xml");
        httppost.addHeader("text", "xml");

        StringEntity entity = null;
        try {
            entity = new StringEntity(xml, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setContentType("application/xml");
        entity.setContentEncoding("application/xml");
        httppost.setEntity(entity);

        HttpResponse response = null;
        try {
            response = client.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        HttpEntity entityRes = response.getEntity();
        InputStream content = null;
        try {
            content = entityRes.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader inputstream = null;
        try {
            inputstream = new InputStreamReader(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputstream);
        String result = null;
        try {
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
