package com.example.guitarpickup;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

public class Excercises extends AppCompatActivity{
    //TODO:Declare Member variables
    private Button mExcercise;
    private Button mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excercise_start);



        //TODO:Connect Buttons with Activity
        mExcercise = (Button)findViewById(R.id.ExcerciseButton);
        mLogout = (Button)findViewById(R.id.logout);

        //TODO:Button listener
        mExcercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),light.class);
                startActivity(intent);
            }
        });
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("loggedin", false);
                editor.putString("username", null);
                editor.apply();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);


            }
        });


    }
}
