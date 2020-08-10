package com.practice.BookBuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    ImageView splashImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashImage=(ImageView) findViewById(R.id.splashlogo);


        Thread myThread= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2500 );

                    Intent i=new Intent(Splash.this,MainActivity.class );
                    startActivity(i);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        myThread.start();

    }
}
