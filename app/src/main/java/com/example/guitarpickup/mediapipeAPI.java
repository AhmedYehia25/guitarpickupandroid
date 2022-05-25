package com.example.guitarpickup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class mediapipeAPI extends AsyncTask<String, String, String> {
    String myUrl = "http://192.168.1.2:8000/api/validate_hands2/";
    JSONArray obj;
    TextView indexf;
    TextView middlef;
    TextView ringf;
    TextView pinkyf;
    mediapipeAPI(JSONArray obj, TextView index, TextView middle, TextView ring, TextView pinky) {
        this.obj = obj;
        this.indexf = index;
        this.middlef = middle;
        this.ringf = ring;
        this.pinkyf = pinky;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... strings) {

        try {

            URL web = new URL(myUrl);
            HttpURLConnection  myConnection = (HttpURLConnection) web.openConnection();
            myConnection.setRequestMethod("POST");
            myConnection.setDoOutput(true);
            myConnection.setDoInput(true);

            String urlParameters  = "right_hand="+obj.toString()+"&left_hand="+obj.toString();
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;

            try( DataOutputStream wr = new DataOutputStream( myConnection.getOutputStream())) {
                wr.write( postData );
            }
            int responseCode = myConnection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("success");
                String result;
                BufferedInputStream bis = new BufferedInputStream(myConnection.getInputStream());
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int result2 = bis.read();
                while(result2 != -1) {
                    buf.write((byte) result2);
                    result2 = bis.read();
                }
                result = buf.toString();
                JSONObject jsonObject = new JSONObject(buf.toString());
                String index = jsonObject.getString("index");
                String middle = jsonObject.getString("middle");
                String ring = jsonObject.getString("ring");
                String pinky = jsonObject.getString("pinky");

                this.publishProgress(index+","+middle+","+ring+","+pinky);

                return "true";

            }
            String result;
            BufferedInputStream bis = new BufferedInputStream(myConnection.getErrorStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result2 = bis.read();
            while(result2 != -1) {
                buf.write((byte) result2);
                result2 = bis.read();
            }
            result = buf.toString();
            System.out.println(result);
            // Error handling code goes here
            return "false";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "false";
    }
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate();
        List<String> liststr = Arrays.asList(values[0].split(","));

        indexf.setText("index: " + liststr.get(0));
        middlef.setText("middle: " + liststr.get(1));
        ringf.setText("ring: " + liststr.get(2));
        pinkyf.setText("pinky: " + liststr.get(3));
    }

}
