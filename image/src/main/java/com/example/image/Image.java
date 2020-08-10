package com.practice.image;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.vision.CameraSource;


public class Image extends AppCompatActivity {

    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID=1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        cameraView=(SurfaceView)findViewById(R.id.surfave_view);
        textView=(TextView)findViewById(R.id.text_view);


        TextRecogniser textRecogniser = new TextRecogniser().Builder(getApplicationContext());
        if(!textRecogniser.isOperational())
        {
            Log.w("MainActivity","Detector Dependencies is not yet available");
        }
    }
}
