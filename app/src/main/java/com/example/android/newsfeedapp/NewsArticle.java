package com.example.android.newsfeedapp;

import java.time.Instant;
import java.util.Date;

/*
* An {@link NewsArticle} object contains information related to a single news article.
*/
public class NewsArticle {

    // tag to indication that date data was used in constructor
    private boolean dateUsed = false;
    private static final boolean DATE_USED = true;
    private static final boolean DATE_NOT_USED = false;

    // tag to indication that author data was used in constructor
    private boolean authorUsed = false;
    private static final boolean AUTHOR_USED = true;
    private static final boolean AUTHOR_NOT_USED = false;

    // required for the project
    //-----------------------------------------------------------------
    private String mSectionName;     // source format - String
    private String mWebTitle;        // source format - String
    private String mWebUrl;          // source format - String
    // required for the project if available
    //-----------------------------------------------------------------
    private String mWebPublicationDate;    // source format - Datetime
    private String mAuthor;                // source format - String
    // example of webTitle - 	"Coronavirus is our chance to completely rethink what the economy is for | Malcolm Bull"
    // author is listed after separator |
    // required for the project if available



    // Constructor with maximum data, including author and webPubilcationDate
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
        dateUsed = DATE_USED;
        authorUsed = AUTHOR_USED;
    }


    // Constructor with minimum data fields used
    /**
     * Constructs a new {@link NewsArticle}.
     *
     * @param sectionName is the section name of the news article
     * @param webTitle is the title of the news article
     * @param webUrl is the webURL for article
     */
    public NewsArticle(String sectionName, String webTitle, String webUrl) {
        mSectionName = sectionName;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        dateUsed = DATE_NOT_USED;
        authorUsed = AUTHOR_NOT_USED;
    }

    // Constructor with webPublicationDate present
    /**
     * Constructs a new {@link NewsArticle}.
     *
     * @param sectionName is the section name of the news article
     * @param webTitle is the title of the news article
     * @param webUrl is the webURL for article
     * @param webPublicationDate is optional data - pubished date of the article (datetime format)
     */
    public NewsArticle(String sectionName, String webTitle, String webUrl, String webPublicationDate) {
        mSectionName = sectionName;
        mWebTitle = String.valueOf(webTitle);
        mWebUrl = String.valueOf(webUrl);
        mWebPublicationDate = webPublicationDate;
        mAuthor = mWebTitle;
        dateUsed = DATE_USED;
        authorUsed = AUTHOR_NOT_USED;
    }

    public String getSectionName() { return mSectionName;}
    public String getWebTitle() {return mWebTitle;}
    public String getUrl() {return mWebUrl;}

    // both methods should check if the article contains the data requested.(date and author of publication)
    public Date getDateTimePublication() {
        return Date.from(Instant.parse(mWebPublicationDate));
    }

    public String getAuthor () {

        // reference https://javarevisited.blogspot.com/2016/10/how-to-check-if-string-contains-another-substring-in-java-indexof-example.html
        boolean isFound = mWebTitle.indexOf("|") != -1;

        // Need to add another condition. The word should have a space to separate first name and last name.
        // Some of words after "|" have "Editorial", "Letter", "Letters" and so on.


        if (isFound) {
            mAuthor = "Author Exist";
        } else {
            mAuthor = "Not available";
        }
        return mAuthor;}

}