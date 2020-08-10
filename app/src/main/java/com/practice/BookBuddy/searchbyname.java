package com.practice.BookBuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class searchbyname extends AppCompatActivity {

    Button button;
    EditText searchQuery;
    String text;
    private String Tag="Process";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyname);

        button=findViewById(R.id.button2);
        searchQuery=findViewById(R.id.searchQuery);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text=searchQuery.getText().toString();
                Log.i(Tag,text+" In String name");
                Intent intent = new Intent(getApplicationContext(),Process.class);
                intent.putExtra("object",text);
                startActivity(intent);
                finish();

            }
        });

    }
}
