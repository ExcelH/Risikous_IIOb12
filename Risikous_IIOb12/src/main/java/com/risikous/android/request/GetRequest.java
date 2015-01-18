package com.risikous.android.request;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Excel on 08.01.2015.
 */
public class GetRequest {

    public String GetXML(String url, String data) {
        HttpClient client = new DefaultHttpClient();

        try {

            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            data = EntityUtils.toString(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
