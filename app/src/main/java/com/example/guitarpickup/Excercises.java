package com.example.guitarpickup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Excercises extends AppCompatActivity{
    //TODO:Declare Member variables
    private Button mExcercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excercise_start);

        //TODO:Connect Buttons with Activity
        mExcercise = (Button)findViewById(R.id.ExcerciseButton);

        //TODO:Button listener
        mExcercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MediaPipe.class);
                startActivity(intent);
            }
        });


    }
}
