package com.example.android.newsappstage1;

public class News {

    final private String mTitle;
    final private String mUrl;
    final private String mCategory;
    final private String mDate;

    public News(String title, String url, String category, String date) {
        this.mTitle = title;
        this.mUrl = url;
        this.mCategory = category;
        this.mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getDate() {
        return mDate;
    }
}
