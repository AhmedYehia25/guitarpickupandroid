package com.example.guitarpickup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;


public class Register extends AppCompatActivity {
    //TODO:Declare Member variables
    String myUrl = "http://192.168.1.20:8000";
    private Button asd;
    EditText edit1;
    EditText edit2;
    EditText edit3;


        // All your networking logic
        // should be here


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        asd = findViewById(R.id.signupButton);
        edit1 = findViewById(R.id.mail);
        edit2 = findViewById(R.id.usrusr);
        edit3 = findViewById(R.id.pswrdd);
        asd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    try {
                        System.out.println("here");
                        Boolean registered = new registerAPI(edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString()).execute().get();
                        if (registered) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Enter a valid username/password/email", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //TODO:Connect Buttons with Activity
    }

    private boolean validateForm() {
        EditText edit4 = findViewById(R.id.confpss);
        if (edit1.length() == 0) {
            edit1.setError("This field is required");
            return false;
        }
        if (edit2.length() == 0) {
            edit2.setError("This field is required");
            return false;
        }
        if (edit3.length() == 0) {
            edit3.setError("This field is required");
            return false;
        }
        if (edit4.length() == 0) {
            edit4.setError("This field is required");
            return false;
        }
        if (!edit3.getText().toString().equals(edit4.getText().toString())) {
            edit3.setError("Passwords must match");
            edit4.setError("Passwords must match");
            return false;
        }
        return true;
    }
}
