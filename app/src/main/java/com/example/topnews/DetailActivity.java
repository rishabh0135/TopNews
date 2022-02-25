package com.example.topnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    WebView myWebView;
    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        myWebView = findViewById(R.id.detailWebView);
        progressBar = findViewById(R.id.progressBar);

       // WebSettings webSettings = myWebView.getSettings();
        String url = getIntent().getStringExtra("URL");
        if(url != null)
        {
            myWebView.getSettings().setJavaScriptEnabled(true);
          //  myWebView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+(KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
            myWebView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                    myWebView.setVisibility(WebView.VISIBLE);
                }
            });
        myWebView.loadUrl(url);
        }
    }
}