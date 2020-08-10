package com.practice.BookBuddy;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Process extends AppCompatActivity {

    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<ModelBookClass> mBooks;
    private RequestQueue mRequestQueue;
    private String Tag="Process";

    private static final String BASE_URL="https://www.googleapis.com/books/v1/volumes?q=";

    private EditText search_edit_text;
    private Button search_button;
    private ProgressBar loading_indicator;
    private TextView error_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);

        Intent intent=getIntent();
        String mText=intent.getStringExtra("object");
        String text=mText.trim();
        if(text.equals("")){
            Toast.makeText(this,"Enter proper text",Toast.LENGTH_SHORT).show();
            Intent in = new Intent(this,MainActivity.class);
            startActivity(in);
            finish();
        }

        loading_indicator = findViewById(R.id.loading_indicator);
        error_message = findViewById(R.id.message_display);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBooks = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        Log.i(Tag,"In onCreate");
        search(text);

    }

    private void search(String text) {

        String search_query= text;
        Log.i(Tag,"In search"+" "+text);
        boolean is_connected= Read_network_state(this);
        if(!is_connected){
            error_message.setText("Failed to get data");
            mRecyclerView.setVisibility(View.INVISIBLE);
            error_message.setVisibility(View.VISIBLE);
            return;
        }

        if(search_query.equals(""))
        {
            Toast.makeText(this,"Please Enter your Query",Toast.LENGTH_SHORT).show();
            return;
        }
        String final_query=search_query.replace(" ","+");
        Uri uri=Uri.parse(BASE_URL+final_query);
        Uri.Builder buider = uri.buildUpon();

        parseJson(buider.toString());

    }

    private void parseJson(String key) {
        Log.i(Tag,"In Json");

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,key.toString()
                ,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.i(Tag,response.toString());
                String title ="";
                String author ="";
                String publishedDate = "NoT Available";
                String description = "No Description";
                int pageCount = 1000;
                String categories = "No categories Available ";
                String buy ="";

                try {
                    JSONArray items =response.getJSONArray("items");

                    for(int i = 0;i<items.length();i++){

                        JSONObject item = items.getJSONObject(i);
                        JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                        String price = "NOT_FOR_SALE";
                        try {
                            title = volumeInfo.getString("title");

                            JSONArray authors = volumeInfo.getJSONArray("authors");
                            if(authors.length() == 1){
                                author = authors.getString(0);
                            }else {
                                author = authors.getString(0) + "|" +authors.getString(1);
                            }

                            publishedDate = volumeInfo.getString("publishedDate");
                            pageCount = volumeInfo.getInt("pageCount");

                            JSONObject saleInfo =item.getJSONObject("saleInfo");
                            JSONObject listPrice = saleInfo.getJSONObject("listPrice");
                            Log.i("price",saleInfo.getString("saleability"));
                            String saleability= saleInfo.getString("saleability");

                            if(saleability.equals("FOR_SALE"))
                                price = listPrice.getString("amount") + " " +listPrice.getString("currencyCode");

                            description = volumeInfo.getString("description");
                            buy = saleInfo.getString("buyLink");
                            categories = volumeInfo.getJSONArray("categories").getString(0);

                        } catch (Exception e) {

                        }
                        String thumbnail = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
                        String previewLink = volumeInfo.getString("previewLink");
                        String url = volumeInfo.getString("infoLink");

                        Log.i(Tag,price);
                        mBooks.add(new ModelBookClass(title , author , publishedDate , description ,categories
                                ,thumbnail,buy,previewLink,price,pageCount,url));
                        Log.i(Tag,mBooks.get(0).getmTitle());
                        Log.i(Tag,mBooks.size()+" size of array");
                        Log.i(Tag,"descriptions : "+description);
                        mAdapter = new RecyclerViewAdapter(Process.this,mBooks);
                        int s=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                                getResources().getDisplayMetrics());
                        mRecyclerView.addItemDecoration(new SpacesItemDecoration(s));
                        mRecyclerView.setAdapter(mAdapter);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Tag",e.toString());

                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    private boolean Read_network_state(Context context) {
        boolean is_connected;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        is_connected=info!=null&&info.isConnectedOrConnecting();
        return is_connected;
    }


}