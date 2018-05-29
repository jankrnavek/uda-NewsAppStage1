package com.example.android.newsappstage1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

public class NewsUtils {
    private static final String LOG_TAG = NewsUtils.class.getSimpleName();

    //    final static String JSON_FAKE_DATA = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":24846,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":2485,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"politics/2018/may/10/tories-accused-of-subverting-democracy-by-not-tabling-brexit-debates\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-05-10T16:09:05Z\",\"webTitle\":\"Tories accused of 'subverting democracy' by not tabling Brexit debates\",\"webUrl\":\"https://www.theguardian.com/politics/2018/may/10/tories-accused-of-subverting-democracy-by-not-tabling-brexit-debates\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/may/10/tories-accused-of-subverting-democracy-by-not-tabling-brexit-debates\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2018/may/04/project-fantasy-german-exam-question-debates-brexit-reality\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-05-04T15:19:29Z\",\"webTitle\":\"Project Fantasy? German exam question debates Brexit reality\",\"webUrl\":\"https://www.theguardian.com/world/2018/may/04/project-fantasy-german-exam-question-debates-brexit-reality\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/may/04/project-fantasy-german-exam-question-debates-brexit-reality\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"science/occams-corner/2017/nov/06/universities-are-part-of-the-solution-to-dysfunctional-brexit-debates\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2017-11-06T11:14:55Z\",\"webTitle\":\"Universities are part of the solution to dysfunctional Brexit debates\",\"webUrl\":\"https://www.theguardian.com/science/occams-corner/2017/nov/06/universities-are-part-of-the-solution-to-dysfunctional-brexit-debates\",\"apiUrl\":\"https://content.guardianapis.com/science/occams-corner/2017/nov/06/universities-are-part-of-the-solution-to-dysfunctional-brexit-debates\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"society/2017/oct/19/paul-keating-says-assisted-dying-unacceptable-as-victoria-debates-law\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2017-10-19T05:30:45Z\",\"webTitle\":\"Paul Keating says assisted dying 'unacceptable' as Victoria debates law\",\"webUrl\":\"https://www.theguardian.com/society/2017/oct/19/paul-keating-says-assisted-dying-unacceptable-as-victoria-debates-law\",\"apiUrl\":\"https://content.guardianapis.com/society/2017/oct/19/paul-keating-says-assisted-dying-unacceptable-as-victoria-debates-law\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"news/2018/apr/26/putting-the-antisemitism-debate-in-perspective\",\"type\":\"article\",\"sectionId\":\"news\",\"sectionName\":\"News\",\"webPublicationDate\":\"2018-04-26T16:31:31Z\",\"webTitle\":\"Putting the antisemitism debate in perspective | Letters\",\"webUrl\":\"https://www.theguardian.com/news/2018/apr/26/putting-the-antisemitism-debate-in-perspective\",\"apiUrl\":\"https://content.guardianapis.com/news/2018/apr/26/putting-the-antisemitism-debate-in-perspective\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/blog/live/2018/jan/09/reshuffle-government-tory-cabinet-theresa-may-not-quite-says-new-tory-chair-when-asked-about-party-being-in-a-mess-politics-live\",\"type\":\"liveblog\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-01-09T17:58:13Z\",\"webTitle\":\"Brexit department announces concessions over EU withdrawal bill ahead of key debates next week - Politics live\",\"webUrl\":\"https://www.theguardian.com/politics/blog/live/2018/jan/09/reshuffle-government-tory-cabinet-theresa-may-not-quite-says-new-tory-chair-when-asked-about-party-being-in-a-mess-politics-live\",\"apiUrl\":\"https://content.guardianapis.com/politics/blog/live/2018/jan/09/reshuffle-government-tory-cabinet-theresa-may-not-quite-says-new-tory-chair-when-asked-about-party-being-in-a-mess-politics-live\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"australia-news/live/2018/may/22/pauline-hanson-reverses-support-for-corporate-tax-cuts-politics-live\",\"type\":\"liveblog\",\"sectionId\":\"australia-news\",\"sectionName\":\"Australia news\",\"webPublicationDate\":\"2018-05-22T07:42:32Z\",\"webTitle\":\"Labor alleges 'secret deal' with One Nation over tax cuts – as it happened\",\"webUrl\":\"https://www.theguardian.com/australia-news/live/2018/may/22/pauline-hanson-reverses-support-for-corporate-tax-cuts-politics-live\",\"apiUrl\":\"https://content.guardianapis.com/australia-news/live/2018/may/22/pauline-hanson-reverses-support-for-corporate-tax-cuts-politics-live\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"music/2018/mar/09/debate-over-nmes-heyday\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2018-03-09T17:05:42Z\",\"webTitle\":\"Debate over NME’s heyday | Letters\",\"webUrl\":\"https://www.theguardian.com/music/2018/mar/09/debate-over-nmes-heyday\",\"apiUrl\":\"https://content.guardianapis.com/music/2018/mar/09/debate-over-nmes-heyday\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"technology/2018/apr/09/killer-robots-pressure-builds-for-ban-as-governments-meet\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2018-04-09T10:35:50Z\",\"webTitle\":\"Killer robots: pressure builds for ban as governments meet\",\"webUrl\":\"https://www.theguardian.com/technology/2018/apr/09/killer-robots-pressure-builds-for-ban-as-governments-meet\",\"apiUrl\":\"https://content.guardianapis.com/technology/2018/apr/09/killer-robots-pressure-builds-for-ban-as-governments-meet\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"science/2018/apr/25/growing-brains-in-labs-why-its-time-for-an-ethical-debate\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2018-04-25T17:07:27Z\",\"webTitle\":\"Growing brains in labs: why it's time for an ethical debate\",\"webUrl\":\"https://www.theguardian.com/science/2018/apr/25/growing-brains-in-labs-why-its-time-for-an-ethical-debate\",\"apiUrl\":\"https://content.guardianapis.com/science/2018/apr/25/growing-brains-in-labs-why-its-time-for-an-ethical-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";
    final static String THEGUARDIAN_URL = "http://content.guardianapis.com/search?q=debates&api-key=test";

    public NewsUtils() {
    }

    public static List<News> getNewsLists() throws IOException {

        URL newsURL = returnUrl(THEGUARDIAN_URL);

        String jsonGuardianContent = makeHttpRequest(newsURL);

        List<News> newsList = extractFeatureFromJson(jsonGuardianContent);

        return newsList;
    }


    private static List<News> extractFeatureFromJson(String jsonContent) {
        if (TextUtils.isEmpty(jsonContent)) {
            return null;
        }

        List<News> news = new ArrayList<>();

        try {
            JSONObject baseJsonNewsResponse = new JSONObject(jsonContent);
            JSONObject responseJsonNews = baseJsonNewsResponse.getJSONObject("response");
            JSONArray newsArray = responseJsonNews.getJSONArray("results");

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject currentItem = newsArray.getJSONObject(i);

                String itemCategory = currentItem.getString("sectionName");
                String itemTitle = currentItem.getString("webTitle");
                String itemUrl = currentItem.getString("webUrl");

                String itemDate = "NA";
                if (currentItem.has("webPublicationDate")) {
                    itemDate = currentItem.getString("webPublicationDate");
                }

                News itemNews = new News(itemTitle, itemUrl, itemCategory, itemDate);
                news.add(itemNews);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot parse JSON content.");
        }

        return news;
    }

    private static URL returnUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "URL building problem.", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {

            Log.e(LOG_TAG, "Connection was not established. Problem retrieving JSON News results.", e);
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}


