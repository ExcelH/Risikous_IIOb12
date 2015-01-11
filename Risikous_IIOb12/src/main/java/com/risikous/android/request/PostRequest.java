package com.risikous.android.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Excel on 11.01.2015.
 */
public class PostRequest {

    public void postXML(String xml, String url) {

        try {
            String data = URLEncoder.encode(xml, "UTF-8");

            URL urlPOST = new URL(url);
            URLConnection conn = urlPOST.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
        }
    }
}
