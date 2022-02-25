package com.example.topnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private List<Article> mListdata;
    private OnNewsListener onNewsListener;

    public NewsAdapter(Context context, List<Article> data, OnNewsListener onNewsListener)
    {
        this.context = context;
        this.mListdata = data;
        this.onNewsListener = onNewsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view, parent,false);
        return new ViewHolder(view, onNewsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article newsData = mListdata.get(position);
        holder.title.setText(newsData.getTitle());
        holder.description.setText(newsData.getNewsProviderName());
        Glide.with(context).load(newsData.getUrlToImage()).into(holder.articleimg);

    }

    @Override
    public int getItemCount()
    {
        return mListdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView description;
        ImageView articleimg;
        OnNewsListener onNewsListener;

        public ViewHolder(@NonNull View itemView, OnNewsListener onNewsListener) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            description = itemView.findViewById(R.id.txtDiscription);
            articleimg = itemView.findViewById(R.id.articleImg);
            this.onNewsListener = onNewsListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNewsListener.onNewsClick(getLayoutPosition());
        }
    }

    public interface OnNewsListener{
        void onNewsClick(int position);
    }
}
