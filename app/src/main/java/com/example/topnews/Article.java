package com.example.topnews;


public class Article {

    private String title;
    private String url;
    private String urlToImage;
    private String providerName;

    public Article(String title, String url, String urlToImage, String name) {
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
        this.providerName = name;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getNewsProviderName() {
        return providerName;
    }
    public void setProviderName(String name) {
        this.providerName = name;
    }
}
