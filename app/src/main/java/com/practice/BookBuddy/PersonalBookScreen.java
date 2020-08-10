package com.practice.BookBuddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PersonalBookScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_book_screen);
        //Receive
        Bundle extras = getIntent().getExtras();
        String title="" ,author="",description="",categories="",publishDate="",preview="",info="",buy="",thumbnail="";
        if(extras !=null){
            title = extras.getString("book_title");
            author = extras.getString("book_author");
            description = extras.getString("book_desc");
            categories = extras.getString("book_categories");
            publishDate = extras.getString("book_publishdate");
            preview = extras.getString("book_preview");
            info = extras.getString("book_info");
            buy = extras.getString("book_buy");
            thumbnail = extras.getString("book_thumbnail");

        }

        TextView tvTitle =findViewById(R.id.aa_book_name);
        TextView tvAuthor = findViewById(R.id.aa_author);
        TextView tvDesc = findViewById(R.id.aa_description);
        TextView tvCatag = findViewById(R.id.aa_category);
        TextView tvPublishDate = findViewById(R.id.aa_publishdate);
        TextView tvInfo = findViewById(R.id.aa_info);
        TextView tvPreview = findViewById(R.id.aa_preview);

        ImageView ivThumbnail = findViewById(R.id.aa_thumbnail);

        tvTitle.setText(title);
        tvAuthor.setText("-"+author);
        tvDesc.setText(description);
        tvCatag.setText(categories);
        tvPublishDate.setText(publishDate);

        final String finalInfo = info;
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));
                startActivity(i);

            }
        });

        final String finalPreview = preview;
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(finalPreview));
                startActivity(i);
            }
        });

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.common_google_signin_btn_icon_disabled).
                error(R.drawable.common_google_signin_btn_text_light_normal_background);

        String url = "https"+thumbnail.substring(4);
        Glide.with(this).load(url).apply(requestOptions).into(ivThumbnail);
    }
}