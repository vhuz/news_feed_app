package com.example.android.newsfeedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** Tag for the log messages */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /** URL to query the Guardian dataset for news information */
    private static final String WEBSITE_REQUEST_URL =
            //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6";
            //"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7";
            "https://content.guardianapis.com/search?api-key=ad2374d4-a9bb-46a9-9d41-0a3cb773578f";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kick off an {@link AsyncTask} to perform the network request
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute();


//        // create list of articles
//        final ArrayList<NewsArticle> articles = new ArrayList<>();
//
//
//        TextView output = (TextView) findViewById(R.id.textView1);
//        String strJson = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2201483,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":220149,\"orderBy\":\"newest\"},\n" +
//                "\"results\":[\n" +
//                "{\"id\":\"business/live/2020/jun/26/stocks-rise-despite-fed-warning-over-700bn-in-covid-19-loan-losses-business-live\",\n" +
//                "\"type\":\"liveblog\",\n" +
//                "\"sectionId\":\"business\",\n" +
//                "\"sectionName\":\"Business\",\n" +
//                "\"webPublicationDate\":\"2020-06-26T12:54:15Z\",\n" +
//                "\"webTitle\":\"Stocks rise despite Fed warning over $700bn in Covid-19 loan losses  - business live | John Smith \",\n" +
//                "\"webUrl\":\"https://www.theguardian.com/business/live/2020/jun/26/stocks-rise-despite-fed-warning-over-700bn-in-covid-19-loan-losses-business-live\",\n" +
//                "\"apiUrl\":\"https://content.guardianapis.com/business/live/2020/jun/26/stocks-rise-despite-fed-warning-over-700bn-in-covid-19-loan-losses-business-live\",\n" +
//                "\"isHosted\":false,\n" +
//                "\"pillarId\":\"pillar/news\",\n" +
//                "\"pillarName\":\"News\"},\n" +
//                "{\"id\":\"football/live/2020/jun/25/liverpool-win-the-premier-league-title-live-reaction\",\n" +
//                "\"type\":\"liveblog\",\n" +
//                "\"sectionId\":\"football\",\n" +
//                "\"sectionName\":\"Football\",\n" +
//                "\"webPublicationDate\":\"2020-06-26T12:53:20Z\",\n" +
//                "\"webTitle\":\"Liverpool are crowned Premier League champions – live reaction!\",\n" +
//                "\"webUrl\":\"https://www.theguardian.com/football/live/2020/jun/25/liverpool-win-the-premier-league-title-live-reaction\",\n" +
//                "\"apiUrl\":\"https://content.guardianapis.com/football/live/2020/jun/25/liverpool-win-the-premier-league-title-live-reaction\",\n" +
//                "\"isHosted\":false,\n" +
//                "\"pillarId\":\"pillar/sport\",\n" +
//                "\"pillarName\":\"Sport\"},\n" +
//                "{\"id\":\"us-news/live/2020/jun/26/mike-pence-coronavirus-elijah-mcclain-donald-trump-live-updates\",\n" +
//                "\"type\":\"liveblog\",\n" +
//                "\"sectionId\":\"us-news\",\n" +
//                "\"sectionName\":\"US news\",\n" +
//                "\"webPublicationDate\":\"2020-06-26T12:49:29Z\",\n" +
//                "\"webTitle\":\"US politics: Mike Pence to lead first public coronavirus task force briefing for months – live updates\",\n" +
//                "\"webUrl\":\"https://www.theguardian.com/us-news/live/2020/jun/26/mike-pence-coronavirus-elijah-mcclain-donald-trump-live-updates\",\n" +
//                "\"apiUrl\":\"https://content.guardianapis.com/us-news/live/2020/jun/26/mike-pence-coronavirus-elijah-mcclain-donald-trump-live-updates\",\n" +
//                "\"isHosted\":false,\n" +
//                "\"pillarId\":\"pillar/news\",\n" +
//                "\"pillarName\":\"News\"}]}";
//        Toast.makeText(this, strJson, Toast.LENGTH_SHORT).show();
//        String data = "";
//        try {
//            JSONObject jsonRootObject = new JSONObject(strJson);
//
//            //Get the instance of JSONArray that contains JSONObjects
//            //JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");
//            JSONArray jsonArray = jsonRootObject.optJSONArray("results");
//
//            // create arrays of empty fields for custom objects NewsArticles
//            String[] article_section = new String[jsonArray.length()];
//            String[] article_title = new String[jsonArray.length()];
//            String[] article_web = new String[jsonArray.length()];
//            String[] article_author = new String[jsonArray.length()];
//
//            //Iterate the jsonArray and print the info of JSONObjects
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                //int id = Integer.parseInt(jsonObject.optString("id").toString());
//                String sectionName = jsonObject.optString("sectionName").toString();
//                //float salary = Float.parseFloat(jsonObject.optString("salary").toString());
//                String webTitle = jsonObject.optString("webTitle").toString();
//                String webUrl = jsonObject.optString("webUrl").toString();
//
//                data += "Node" + i + " : \n " +
//                        "Section= " + sectionName +
//                        "\n Title= " + webTitle +
//                        "\n Web= " + webUrl + " \n";
//                article_section[i] = sectionName;
//                article_title[i] = webTitle;
//                article_web[i] = webUrl;
//                article_author[i] = "Not known author";
//
//                // to find out if author of article can be retrieved
//                boolean isFound = webTitle.indexOf("|") != -1;
//
//                if (isFound) {
//                    String author = webTitle.substring((webTitle.indexOf("|") + 2));
//                    data += "\n Author: " + author + "\n ";
//                }
//
//                data += "\n Current Date: " + (new DateTimeFormat("")).getCurrentDate() + "\n";
//                data += "\n Current Raw Date: " + (new DateTimeFormat("")).getRawDateTime() + "\n";
//                data += "\n Current Time: " + (new DateTimeFormat("")).getCurrentTime() + "\n";
//
//                articles.add(new NewsArticle(article_section[i], article_title[i], article_web[i], article_web[i], article_author[i]));
//
//            }
//
//            output.setText(data);
//            //output.setText(articles.toString());
//
//        } catch (JSONException e) {
//            //Toast.makeText(this, "error" + e, Toast.LENGTH_LONG).show();
//            Log.e("Error", String.valueOf(e));
//        }


    }



        // the code below is to be removed.............................
        // .............................................................................

//        // Get the list of earthquakes from {@link QueryUtils}
//        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
//
//        // Find a reference to the {@link ListView} in the layout
//        ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
//        // Create a new adapter that takes the list of earthquakes as input
//        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);
//
//        // Set an item click listener on the ListView, which sends an intent to a web browser
//        // to open a website with more information about the selected earthquake.
//        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                // Find the current earthquake that was clicked on
//                Earthquake currentEarthquake = adapter.getItem(position);
//
//                // Convert the String URL into a URI object (to pass into the Intent constructor)
//                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
//
//                // Create a new intent to view the earthquake URI
//                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
//
//                // Send the intent to launch a new activity
//                startActivity(websiteIntent);
//            }
//        });



    /**
     * Update the screen to display information from the given {@link NewsArticle}.
     */
    private void updateUi(NewsArticle newsArticle) {

        TextView output = (TextView) findViewById(R.id.textView1);
        output.setText(newsArticle.getWebTitle());
//        // Display the earthquake title in the UI
//        TextView titleTextView = (TextView) findViewById(R.id.title);
//        titleTextView.setText(earthquake.title);
//
//        // Display the earthquake date in the UI
//        TextView dateTextView = (TextView) findViewById(R.id.date);
//        dateTextView.setText(getDateString(earthquake.time));
//
//        // Display whether or not there was a tsunami alert in the UI
//        TextView tsunamiTextView = (TextView) findViewById(R.id.tsunami_alert);
//        tsunamiTextView.setText(getTsunamiAlertString(earthquake.tsunamiAlert));
    }

    /**
     * Returns a formatted date and time string for when the earthquake happened.
     */
    private String getDateString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }

    /**
     * Return the display string for whether or not there was a tsunami alert for an earthquake.
     */
    private String getTsunamiAlertString(int tsunamiAlert) {
        switch (tsunamiAlert) {
            case 0:
                return getString(R.string.alert_no);
            case 1:
                return getString(R.string.alert_yes);
            default:
                return getString(R.string.alert_not_available);
        }
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class NewsAsyncTask extends AsyncTask<URL, Void, NewsArticle> {

        @Override
        protected NewsArticle doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(WEBSITE_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            NewsArticle newsArticle = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link NewsAsyncTask}
            return newsArticle;
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link NewsAsyncTask}).
         */
        @Override
        protected void onPostExecute(NewsArticle newsArticle) {
            if (newsArticle == null) {
                return;
            }

            updateUi(newsArticle);
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                // TODO: Handle the exception
                Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
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

        /**
         * Return an {@link NewsArticle} object by parsing out information
         * about the first earthquake from the input earthquakeJSON string.
         */
        private NewsArticle extractFeatureFromJson(String newsJSON) {

            // if the JSON string is empty or null, then return early.
            if (TextUtils.isEmpty(newsJSON)) {
                return null;
            }

            try {
//            try {
//                JSONObject baseJsonResponse = new JSONObject(newsJSON);
//                JSONArray featureArray = baseJsonResponse.getJSONArray("features");
//
//                // If there are results in the features array
//                if (featureArray.length() > 0) {
//                    // Extract out the first feature (which is an earthquake)
//                    JSONObject firstFeature = featureArray.getJSONObject(0);
//                    JSONObject properties = firstFeature.getJSONObject("properties");
//
//                    // Extract out the title, time, and tsunami values
//                    String title = properties.getString("title");
//                    long time = properties.getLong("time");
//                    int tsunamiAlert = properties.getInt("tsunami");
//
//                    // Create a new {@link Event} object
//                    return new NewsArticle(title, time, tsunamiAlert);
//                }


                JSONObject baseJsonResponse = new JSONObject(newsJSON);

                //Get the instance of JSONArray that contains JSONObjects
                JSONObject firstFeature = baseJsonResponse.getJSONObject("response");
                JSONArray jsonArray = firstFeature.optJSONArray("results");

                // If there are results in the jsonArray
                if (jsonArray.length() > 0) {
//                    // create arrays of empty fields for custom objects NewsArticles
//                    String[] article_section = new String[jsonArray.length()];
//                    String[] article_title = new String[jsonArray.length()];
//                    String[] article_web = new String[jsonArray.length()];
//                    String[] article_author = new String[jsonArray.length()];
//
//                    //Iterate the jsonArray and print the info of JSONObjects
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                        //int id = Integer.parseInt(jsonObject.optString("id").toString());
//                        String sectionName = jsonObject.optString("sectionName").toString();
//                        //float salary = Float.parseFloat(jsonObject.optString("salary").toString());
//                        String webTitle = jsonObject.optString("webTitle").toString();
//                        String webUrl = jsonObject.optString("webUrl").toString();
//                    }
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String sectionName = jsonObject.optString("sectionName").toString();
                    String webTitle = jsonObject.optString("webTitle").toString();
                    String webUrl = jsonObject.optString("webUrl").toString();

                    // Create a new {@link NewsArticle} object
                    return new NewsArticle(sectionName, webTitle, webUrl);
                }}
                catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }

    }
}