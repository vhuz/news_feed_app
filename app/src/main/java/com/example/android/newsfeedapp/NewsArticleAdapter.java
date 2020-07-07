/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.newsfeedapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * An {@link NewsArticleAdapter} knows how to create a list item layout for each news article
 * in the data source (a list of {@link NewsArticle} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsArticleAdapter extends ArrayAdapter<NewsArticle> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link NewsArticleAdapter}.
     *
     * @param context of the app
     * @param articles is the list of news articles, which is the data source of the adapter
     */
    public NewsArticleAdapter(Context context, List<NewsArticle> articles) {
        super(context, 0, articles);
    }

    //TODO: Work on NewsArticleAdapter to show article fields - Section, webTitle, webURL, Author, Date

    /**
     * Returns a list item view that displays information about the news article at the given position
     * in the list of articles.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        // Find the news article at the given position in the list of articles
        NewsArticle currentActicle = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.web_title);

        // Format the magnitude to show 1 decimal place
        //String formattedMagnitude = formatMagnitude(currentActicle.getMagnitude());
        String formattedMagnitude = currentActicle.getWebTitle();
        //String formattedMagnitude ="Placeholder Magnitude";

        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        String originalLocation = currentActicle.getSectionName();

        // Find the TextView with view ID location
        TextView webTitleView = (TextView) listItemView.findViewById(R.id.web_title);
        // Display the location of the current earthquake in that TextView
        webTitleView.setText(formattedMagnitude);

        // Find the TextView with view ID location offset
        TextView sectionNameView = (TextView) listItemView.findViewById(R.id.section_name);
        // Display the location offset of the current earthquake in that TextView
        sectionNameView.setText(originalLocation);


        // The date is optional field, therefore needed to have a condition.
        //Date Obj = new Date();
        Date dateObject = currentActicle.getDateTimePublication();


        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current article in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current article in that TextView
        timeView.setText(formattedTime);


        //        // Display the earthquake date in the UI
//        TextView dateTextView = (TextView) findViewById(R.id.textView3);
//        dateTextView.setText(newsArticle.getDateTimePublication());


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Returns a formatted date and time string for when the earthquake happened.
     */
    private String getDateString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }

}
