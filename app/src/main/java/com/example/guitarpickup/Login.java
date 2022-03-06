package com.example.guitarpickup;

import android.content.Intent;
import android.content.SharedPreferences;
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


public class Login extends AppCompatActivity {
    //TODO:Declare Member variables
    String myUrl = "http://192.168.1.20:8000";
    private Button lLogin;
    EditText edit1;
    EditText edit2;
    EditText edit3;


    // All your networking logic
    // should be here


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lLogin = findViewById(R.id.signButton);
        edit1 = findViewById(R.id.editTextEmail);
        edit2 = findViewById(R.id.editTextPassword);
        lLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    try {
                        Boolean registered = new loginAPI(edit1.getText().toString(), edit2.getText().toString()).execute().get();
                        if (registered) {
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("loggedin", true);
                            editor.putString("username", String.valueOf(edit1.getText()));
                            editor.apply();
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Enter a valid username/password", Toast.LENGTH_LONG);
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

    }

    private boolean validateForm() {
        if (edit1.length() == 0) {
            lLogin.setError("This field is required");
            return false;
        }
        return true;
    }
}