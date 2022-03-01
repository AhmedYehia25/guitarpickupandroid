package com.example.guitarpickup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class light extends AppCompatActivity {
    //TODO:Declare Member variables


    // All your networking logic
    // should be here
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting);
        next = (Button) findViewById(R.id.nextLight);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), hands.class);
                startActivity(intent);
            }
        });
        //TODO:Connect Buttons with Activity
    }

}
