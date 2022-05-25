package com.example.guitarpickup;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

class loginAPI extends AsyncTask<Void, Void, Boolean> {
    String myUrl = "http://138.68.160.89/api/login/";
    String username, pass;
    loginAPI(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        try {

            URL web = new URL(myUrl);
//            String auth = "adminahmed:mohamed13";
//            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
//            String authHeaderValue = "Basic " + new String(encodedAuth);
            HttpURLConnection  myConnection = (HttpURLConnection) web.openConnection();
            myConnection.setRequestMethod("POST");
            //myConnection.setRequestProperty("Authorization", authHeaderValue);
            myConnection.setDoOutput(true);

            String urlParameters  = "username="+username+"&password="+pass;
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;

            try( DataOutputStream wr = new DataOutputStream( myConnection.getOutputStream())) {
                wr.write( postData );
            }
            int responseCode = myConnection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("success");

                return true;

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
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
