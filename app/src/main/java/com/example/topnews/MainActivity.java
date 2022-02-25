package com.example.topnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NewsAdapter.OnNewsListener{

    private static final String URL = "https://saurav.tech/NewsAPI/top-headlines/category/business/in.json";

    //ui component
    private  RecyclerView mRecyclerView;
    //vars---
    private NewsAdapter mNewsAdapter;
    private List<Article> mListdata;
    //RequestQueue for volley---
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.newsList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mListdata = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        fetchNewsData();
    }


    private void fetchNewsData() {

       //String URL = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, response -> {
            try {
                JSONArray jsonArray = response.getJSONArray("articles");
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject article = jsonArray.getJSONObject(i);

                    String title = article.getString("title");
                    String url = article.getString("url");
                    String imageUrl = article.getString("urlToImage");

                    JSONObject source = article.getJSONObject("source");
                    String providerName = source.getString("name");

                    mListdata.add(new Article(title, url, imageUrl, providerName));
                }

                mNewsAdapter = new NewsAdapter(MainActivity.this,mListdata, this);
                mRecyclerView.setAdapter(mNewsAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> error.printStackTrace());

        mRequestQueue.add(request);
}

    @Override
    public void onNewsClick(int position) {
        mListdata.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("URL", mListdata.get(position).getUrl());
        startActivity(intent);
    }
}