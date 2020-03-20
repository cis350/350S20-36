package edu.upenn.cis350.androidapp;

import java.net.*;
import android.os.*;
import java.util.*;
import org.json.*;
import java.io.*;

public class HttpPostWebTask extends AsyncTask<URL, String, String> {

    private JSONObject postData;

    public HttpPostWebTask(JSONObject postData) {
        this.postData = postData;
    }

    protected String doInBackground(URL... urls){

        try {
            // get the first URL from the array
            URL url = urls[0];

            // create connection and send HTTP request
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("POST");
            conn.connect();

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(postData.toString());
            writer.flush();

            // read first line of data that is returned
            Scanner in = new Scanner(url.openStream());
            String msg = in.nextLine();

            // use Android JSON library to parse JSON
            JSONObject jo = new JSONObject(msg);

            return jo.toString();
        }
        catch (Exception e) {
            return e.toString();
        }

    }

    // called after doInBackground finishes
    protected void onPostExecute(String msg) {

    }

}