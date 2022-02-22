package com.example.guitarpickup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    //TODO:Declare Member variables
    private Button mLogin;
    private Button mRegister;
    private EditText edit1;
    private EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO:Connect Buttons with Activity
        mLogin = (Button)findViewById(R.id.LoginButton);
        mRegister = (Button)findViewById(R.id.registerButton);
        edit1 = findViewById(R.id.editText2);
        edit2 = findViewById(R.id.editText3);

        //TODO:Button listener
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    try {
                        Boolean registered = new loginAPI(edit1.getText().toString(), edit2.getText().toString()).execute().get();
                        if (registered) {
                            Intent intent = new Intent(getApplicationContext(),Excercises.class);
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
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });


    }

    private boolean validateForm() {
        if (edit1.length() == 0) {
            mLogin.setError("This field is required");
            return false;
        }
        if (edit2.length() == 0) {
            mRegister.setError("This field is required");
            return false;
        }
        return true;
    }

}