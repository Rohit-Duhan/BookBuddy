package com.practice.BookBuddy;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class searchbymic extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1002;
    TextView textView;
    public Button voicebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "inside search by mic class - on create", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_searchbymic);

        textView = (TextView) findViewById(R.id.textviewmic);
        voicebtn = (Button) findViewById(R.id.btnSearch);
        voicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Process.class);
                intent.putExtra("object",textView.getText().toString());
                startActivity(intent);
                finish();

            }
        });
    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));
                }
                break;
        }
        //Toast.makeText(this,txvResult.getText().toString(),Toast.LENGTH_LONG).show();
        if(textView.getText().toString().equals(null))
        {

        }
        else {
            textView.setVisibility(View.VISIBLE);
            voicebtn.setVisibility(View.VISIBLE);
        }

    }
}
