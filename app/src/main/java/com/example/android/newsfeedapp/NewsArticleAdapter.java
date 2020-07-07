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
     * Constructs a new {@link NewsArticleAdapter}.
     *
     * @param context of the app
     * @param articles is the list of news articles, which is the data source of the adapter
     */
    public NewsArticleAdapter(Context context, List<NewsArticle> articles) {
        super(context, 0, articles);
    }

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

        // Find the TextView with view the title of the news article
        TextView titleView = listItemView.findViewById(R.id.web_title);
        // Display the title of the current article in that TextView
        titleView.setText(currentActicle.getWebTitle());

        // Find the TextView with ID for section name
        TextView sectionNameView = listItemView.findViewById(R.id.section_name);
        // Display the section name in that TextView
        sectionNameView.setText(currentActicle.getSectionName());

        // The date is an optional field, therefore needed to be checked if currentArticle has it.
        if (currentActicle.hasDate()) {

            Date dateObject = currentActicle.getDateTimePublication();
            // Find the TextView with view ID date
            TextView dateView = listItemView.findViewById(R.id.date);
            // Format the date string (i.e. "Mar 3, 1984")
            String formattedDate = formatDate(dateObject);
            // Display the date of the current article in that TextView
            dateView.setText(formattedDate);

            // Find the TextView with view ID time
            TextView timeView = listItemView.findViewById(R.id.time);
            // Format the time string (i.e. "4:30PM")
            String formattedTime = formatTime(dateObject);
            // Display the time of the current article in that TextView
            timeView.setText(formattedTime);
        }

        // The author is an optional field, therefore needed to be checked if currentArticle has it.
        if (currentActicle.hasAuthor()) {
            //  Find the TextView with view ID author
            TextView authorTextView = listItemView.findViewById(R.id.author);
            // Display the author of the current article in that TextView
            authorTextView.setText(currentActicle.getAuthor());
            authorTextView.setVisibility(View.VISIBLE);
        } else {
            //  Find the TextView with view ID author
            TextView authorTextView = listItemView.findViewById(R.id.author);
            authorTextView.setVisibility(View.GONE);
        }

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


}
