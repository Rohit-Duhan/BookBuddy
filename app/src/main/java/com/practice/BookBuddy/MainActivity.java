package com.practice.BookBuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button searchbyname,searchbyimage,searchbyvoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        searchbyvoice = findViewById(R.id.searchbyvoice);
        searchbyvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),searchbymic.class);
                startActivity(intent);
            }
        });

        searchbyimage = findViewById(R.id.searchbyimage);
        searchbyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),searchbyimage.class);
                startActivity(intent);
            }
        });


        searchbyname = findViewById(R.id.searchbyname);
        searchbyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),searchbyname.class);
                startActivity(intent);


            }
        });
    }



}

