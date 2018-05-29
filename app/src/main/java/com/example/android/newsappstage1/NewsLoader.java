package com.example.android.newsappstage1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private static final String LOG_TAG = NewsLoader.class.getSimpleName();

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {

        List<News> newsList = new ArrayList<>();

        try {
            newsList = NewsUtils.getNewsLists();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Cannot get News data.");
        }

        return newsList;
    }
}
