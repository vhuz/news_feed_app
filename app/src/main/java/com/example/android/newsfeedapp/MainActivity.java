package com.example.android.newsfeedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create list of articles
        final ArrayList<NewsArticle> articles = new ArrayList<>();


        TextView output = (TextView) findViewById(R.id.textView1);
        String strJson= "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2201483,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":220149,\"orderBy\":\"newest\"},\n" +
                "\"results\":[\n" +
                "{\"id\":\"business/live/2020/jun/26/stocks-rise-despite-fed-warning-over-700bn-in-covid-19-loan-losses-business-live\",\n" +
                "\"type\":\"liveblog\",\n" +
                "\"sectionId\":\"business\",\n" +
                "\"sectionName\":\"Business\",\n" +
                "\"webPublicationDate\":\"2020-06-26T12:54:15Z\",\n" +
                "\"webTitle\":\"Stocks rise despite Fed warning over $700bn in Covid-19 loan losses  - business live | John Smith \",\n" +
                "\"webUrl\":\"https://www.theguardian.com/business/live/2020/jun/26/stocks-rise-despite-fed-warning-over-700bn-in-covid-19-loan-losses-business-live\",\n" +
                "\"apiUrl\":\"https://content.guardianapis.com/business/live/2020/jun/26/stocks-rise-despite-fed-warning-over-700bn-in-covid-19-loan-losses-business-live\",\n" +
                "\"isHosted\":false,\n" +
                "\"pillarId\":\"pillar/news\",\n" +
                "\"pillarName\":\"News\"},\n" +
                "{\"id\":\"football/live/2020/jun/25/liverpool-win-the-premier-league-title-live-reaction\",\n" +
                "\"type\":\"liveblog\",\n" +
                "\"sectionId\":\"football\",\n" +
                "\"sectionName\":\"Football\",\n" +
                "\"webPublicationDate\":\"2020-06-26T12:53:20Z\",\n" +
                "\"webTitle\":\"Liverpool are crowned Premier League champions – live reaction!\",\n" +
                "\"webUrl\":\"https://www.theguardian.com/football/live/2020/jun/25/liverpool-win-the-premier-league-title-live-reaction\",\n" +
                "\"apiUrl\":\"https://content.guardianapis.com/football/live/2020/jun/25/liverpool-win-the-premier-league-title-live-reaction\",\n" +
                "\"isHosted\":false,\n" +
                "\"pillarId\":\"pillar/sport\",\n" +
                "\"pillarName\":\"Sport\"},\n" +
                "{\"id\":\"us-news/live/2020/jun/26/mike-pence-coronavirus-elijah-mcclain-donald-trump-live-updates\",\n" +
                "\"type\":\"liveblog\",\n" +
                "\"sectionId\":\"us-news\",\n" +
                "\"sectionName\":\"US news\",\n" +
                "\"webPublicationDate\":\"2020-06-26T12:49:29Z\",\n" +
                "\"webTitle\":\"US politics: Mike Pence to lead first public coronavirus task force briefing for months – live updates\",\n" +
                "\"webUrl\":\"https://www.theguardian.com/us-news/live/2020/jun/26/mike-pence-coronavirus-elijah-mcclain-donald-trump-live-updates\",\n" +
                "\"apiUrl\":\"https://content.guardianapis.com/us-news/live/2020/jun/26/mike-pence-coronavirus-elijah-mcclain-donald-trump-live-updates\",\n" +
                "\"isHosted\":false,\n" +
                "\"pillarId\":\"pillar/news\",\n" +
                "\"pillarName\":\"News\"}]}";
        Toast.makeText(this, strJson, Toast.LENGTH_SHORT).show();
        String data = "";
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            //JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");
            JSONArray jsonArray = jsonRootObject.optJSONArray("results");

            // create arrays of empty fields for custom objects NewsArticles
            String[] article_section = new String[jsonArray.length()];
            String[] article_title = new String[jsonArray.length()];
            String[] article_web = new String[jsonArray.length()];
            String[] article_author = new String[jsonArray.length()];

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //int id = Integer.parseInt(jsonObject.optString("id").toString());
                String sectionName = jsonObject.optString("sectionName").toString();
                //float salary = Float.parseFloat(jsonObject.optString("salary").toString());
                String webTitle = jsonObject.optString("webTitle").toString();
                String webUrl = jsonObject.optString("webUrl").toString();

                data += "Node"+i+" : \n " +
                        "Section= " + sectionName +
                        "\n Title= "+ webTitle +
                        "\n Web= " + webUrl + " \n";
                article_section[i] = sectionName;
                article_title[i] = webTitle;
                article_web[i] = webUrl;
                article_author[i] = "Not known author";

                // to find out if author of article can be retrieved
                boolean isFound = webTitle.indexOf("|") != -1;

                if (isFound) {
                    String author = webTitle.substring((webTitle.indexOf("|")+2));
                    data += "\n Author: " + author + "\n ";
                }

                data += "\n Current Date: " + (new DateTimeFormat("")).getCurrentDate() + "\n";
                data += "\n Current Raw Date: " + (new DateTimeFormat("")).getRawDateTime()+ "\n";
                data += "\n Current Time: " + (new DateTimeFormat("")).getCurrentTime()+ "\n";

                articles.add(new NewsArticle(article_section[i],article_title[i],article_web[i], article_web[i],article_author[i]));

            }

           output.setText(data);
            //output.setText(articles.toString());

        } catch (JSONException e ) {
            //Toast.makeText(this, "error" + e, Toast.LENGTH_LONG).show();
            Log.e("Error", String.valueOf(e));}

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

    }
}