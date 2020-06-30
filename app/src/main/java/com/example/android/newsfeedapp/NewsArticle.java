package com.example.android.newsfeedapp;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


/*
* An {@link NewsArticle} object contains information related to a single news article.
*/
public class NewsArticle {

    private String mId;
    private String mType;
    private String mSectionId;
    // required for the project
    // source format - String
    private String mSectionName;
    // required for the project if available
    // source format - Datetime
    private DateTimeFormat mDateTimePublicationDate;
    private String mWebPublicationDate;
    // required for the project
    // source format - String
    private String mWebTitle;
    // need so we can pass it in intent for webbrowser
    // source format - String
    private String mWebUrl;
    private String mApiUrl;
    private String mIsHosted;
    private String mPillarId;
    private String mPillarName;
    // example of webTitle - 	"Coronavirus is our chance to completely rethink what the economy is for | Malcolm Bull"
    // author is listed after separator |
    // required for the project if available
    // source format - String
    private String mAuthor;

    /**
     * Constructs a new {@link NewsArticle} object.
     *
     * @param sectionName is the section article belong
     * @param webTitle is the title of the article
     * @param webPublicationDate date of the publication (if available)
     * @param author - author of the publication (if available)
     * @param url is the website URL to find more details and article itself
     */
    public NewsArticle(String sectionName, String webTitle, String webPublicationDate, String author, String url) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebPublicationDate = webPublicationDate;
        mAuthor = author;
        mWebUrl = url;
    }

    /**
     * Constructs a new {@link NewsArticle}.
     *
     * @param eventTitle is the title of the earthquake event
     * @param eventTime is the time the earhtquake happened
     * @param eventTsunamiAlert is whether or not a tsunami alert was issued
     */
    public NewsArticle(String eventTitle, long eventTime, int eventTsunamiAlert) {
        mWebTitle = eventTitle;
        mWebPublicationDate = String.valueOf(eventTime);
        mAuthor = String.valueOf(eventTsunamiAlert);
    }

    public NewsArticle(String sectionName, String webTitle, String webUrl) {
        mSectionName = sectionName;
        mWebTitle = String.valueOf(webTitle);
        mWebUrl = String.valueOf(webUrl);
    }

    public NewsArticle(String sectionName, String webTitle, String webUrl, String webPublicationDate) {
        mSectionName = sectionName;
        mWebTitle = String.valueOf(webTitle);
        mWebUrl = String.valueOf(webUrl);
        mWebPublicationDate = webPublicationDate;
    }


    public String getSectionName() { return mSectionName;}
    public String getWebTitle() {return mWebTitle;}
    public String getWebUrl() {return mWebUrl;}
    // both methods should check if the article contains the data requested.(date and author of publication)

    public String getDateTimePublication() {
        String mRawDateTime;

        DateTimeFormat myDateObj;
        myDateObj = mDateTimePublicationDate;
        mRawDateTime = myDateObj.toString();

        return mRawDateTime;
    }





    public String getWebPublicationDate() {

        //mWebPublicationDate = "2020-06-26T12:49:29Z";
        //d = ((DateTime) mWebPublicationDate);

        return getSimpleDateString(mWebPublicationDate);}


        // reference https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
        private String getSimpleDateString(String fullDateTime) {
            String output;
            SimpleDateFormat formatter;
            String pattern = "EEE',' dd MMM yyyy";
            String currentLocale = "en_US";

            formatter = new SimpleDateFormat(pattern, Locale.forLanguageTag(currentLocale));

            Date datePublished = Date.from(Instant.parse(fullDateTime));

            output = formatter.format(datePublished);
            System.out.println(pattern + " " + output);

            return output;
        }


    public String getAuthor () {

        // reference https://javarevisited.blogspot.com/2016/10/how-to-check-if-string-contains-another-substring-in-java-indexof-example.html
        boolean isFound = mWebTitle.indexOf("|") != -1;
        if (isFound) {
            mAuthor = "Author Exist";
        } else {
            mAuthor = "Not available";
        }
        return mAuthor;}

}