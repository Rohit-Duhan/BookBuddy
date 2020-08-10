package com.practice.BookBuddy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

  private List<ModelBookClass> mData;
  private Context mContext;
  private RequestOptions options;

    public RecyclerViewAdapter(Context mContext, List<ModelBookClass> mData) {
        this.mData = mData;
        this.mContext = mContext;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.common_google_signin_btn_icon_light_normal_background);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row,parent,false);

        final MyViewHolder viewHolder =  new MyViewHolder(view);
        try{
            Log.i("process",viewHolder.container.toString());
            viewHolder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext , PersonalBookScreen.class);
                    int pos = viewHolder.getAdapterPosition();
                    i.putExtra("book_title" ,mData.get(pos).getmTitle());
                    i.putExtra("book_author" ,mData.get(pos).getmAuthors());
                    i.putExtra("book_desc" ,mData.get(pos).getmDescription());
                    i.putExtra("book_categories" ,mData.get(pos).getmCategories());
                    i.putExtra("book_publish_date" ,mData.get(pos).getmPublishedDate());
                    i.putExtra("book_info" ,mData.get(pos).getmUrl());
                    i.putExtra("book_buy" ,mData.get(pos).getmBuy());
                    i.putExtra("book_preview" ,mData.get(pos).getmPreview());
                    i.putExtra("book_thumbnail" ,mData.get(pos).getmThumbnail());


                    mContext.startActivity(i);
                }
            });
        }catch (Exception e){
            Log.i("process",e.toString());
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        ModelBookClass book = mData.get(i);
        holder.tvTitle.setText(book.getmTitle());
        holder.tvAuthor.setText(book.getmAuthors());
        holder.tvPrice.setText(book.getmPrice());
        holder.tvCategory.setText(book.getmCategories());
        Log.i("price",book.getmPrice()+" ");

        //load book image from glide
        //Glide.with(holder.itemView.getContext()).load(mData.get(position)).transform(new CenterCrop(),new RoundedCorners(16))
          //      .into(holder.ivThumbnail);
        String url="https"+book.getmThumbnail().substring(4);
        Glide.with(mContext).load(url).apply(options).into(holder.ivThumbnail);

    }

    @Override
    public int getItemCount() {
      return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivThumbnail;
        TextView tvTitle , tvCategory , tvPrice , tvAuthor;
        ConstraintLayout container ;

     public MyViewHolder(@NonNull View itemView) {
         super(itemView);

         ivThumbnail = itemView.findViewById(R.id.thumbnail);
         tvTitle = itemView.findViewById(R.id.title);
         tvAuthor = itemView.findViewById(R.id.author);
         tvCategory = itemView.findViewById(R.id.category);
         tvPrice = itemView.findViewById(R.id.price);
         container = itemView.findViewById(R.id.container);
     }
 }
}
