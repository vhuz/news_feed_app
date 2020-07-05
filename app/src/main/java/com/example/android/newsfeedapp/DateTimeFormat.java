package com.example.android.newsfeedapp;

import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// reference https://www.w3schools.com/java/java_date.asp

public class DateTimeFormat {

    private static String mFormattedDate;
    private String mRawDateTime;
    private String mFormattedTime;

    public DateTimeFormat(String formattedDate) {
        //String mFormattedDate = "";
        // required min API 26
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myDateFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter myTimeFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");

        mFormattedDate = myDateObj.format(myDateFormatObj);
        mFormattedTime = myDateObj.format(myTimeFormatObj);
        mRawDateTime = myDateObj.toString();

//        System.out.println("After formatting: " + mFormattedDate);
//        Log.i("Format has completed", String.valueOf(mFormattedDate));

    }

    public static String getCurrentDate() {return mFormattedDate;}
    public String getRawDateTime() {return mRawDateTime;}
    public String getCurrentTime() {return mFormattedTime;}

}
