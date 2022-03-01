package com.example.guitarpickup;


import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.List;

public class cameraActivity extends Activity {

    private Camera mCamera;
    private Button button;
    private cameraChecker mPreview;
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    private Camera openFrontFacingCameraGingerbread() {
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    cam = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    Log.e("asdsd", "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
        }

        return cam;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_checker);

        // Create an instance of Camera
        mCamera = openFrontFacingCameraGingerbread();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new cameraChecker(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        button = (Button) findViewById(R.id.button_capture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MediaPipe.class);
                startActivity(intent);
            }
        });

        preview.addView(mPreview);
    }
}